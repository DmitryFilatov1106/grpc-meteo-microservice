package ru.fildv.datastoregrpcmicroservice.service;

import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.fildv.datastoregrpcmicroservice.model.Indicator;
import ru.fildv.grpcmeteo.AnalyticsServerGrpc;
import ru.fildv.grpcmeteo.GAnalyticsRequest;
import ru.fildv.grpcmeteo.GIndicator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class GDataServiceImpl implements GDataService {
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final SummaryService summaryService;

    @GrpcClient(value = "data-store-async")
    private AnalyticsServerGrpc.AnalyticsServerStub asyncStub;

    @Value("${fetch.batch-size}")
    private int batchSize;

    @PostConstruct
    public void init() {
        fetchMessages();
    }

    @Override
    @SneakyThrows
    public void fetchMessages() {
        executorService.scheduleAtFixedRate(
                () -> asyncStub.getIndicators(GAnalyticsRequest.newBuilder()
                                .setBatchSize(batchSize)
                                .build(),
                        new StreamObserver<>() {
                            @Override
                            public void onNext(GIndicator gIndicator) {
                                summaryService.save(new Indicator(gIndicator));
                            }

                            @Override
                            public void onError(Throwable throwable) {
                            }

                            @Override
                            public void onCompleted() {
                                log.info("Batch was handled.");
                            }
                        }
                ), 0, 10, TimeUnit.SECONDS
        );
    }
}
