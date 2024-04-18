package ru.fildv.grpcmeteogeneratormicroservice.service;

import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.fildv.grpcmeteo.GIndicator;
import ru.fildv.grpcmeteo.GMeteoType;
import ru.fildv.grpcmeteo.MeteoServerGrpc;
import ru.fildv.grpcmeteogeneratormicroservice.model.Indicator;

import java.time.ZoneOffset;
import java.util.List;

@Service
@Slf4j
public class GIndicatorServiceImpl implements GIndicatorService {
    @GrpcClient(value = "indicator-generator-blocking")
    private MeteoServerGrpc.MeteoServerBlockingStub blockingStub;

    @GrpcClient(value = "indicator-generator-async")
    private MeteoServerGrpc.MeteoServerStub asyncStub;

    @Override
    public void send(Indicator indicator) {
        GIndicator request = getGIndicator(indicator);
        log.info("GIndicator is ready!!");
        var resultEmpty = blockingStub.addIndicator(request);

        /* либо так
         StreamObserver<Empty> responseObserver = getResponseObserver();
         asyncStub.addIndicator(request, responseObserver);
         */
    }

    @Override
    public void send(List<Indicator> indicators) {
        StreamObserver<Empty> responseObserver = getResponseObserver();
        StreamObserver<GIndicator> observer = asyncStub.addStreamOfIndicator(responseObserver);
        for (Indicator d : indicators) {
            GIndicator request = getGIndicator(d);
            observer.onNext(request);
        }
        observer.onCompleted();
    }

    private static GIndicator getGIndicator(Indicator indicator) {
        return GIndicator.newBuilder()
                .setMeteoId(indicator.getMeteoId())
                .setTimestamp(
                        Timestamp.newBuilder()
                                .setSeconds(
                                        indicator.getTimestamp().toEpochSecond(ZoneOffset.UTC)
                                )
                                .build())
                .setValue(indicator.getValue())
                .setMeteoType(GMeteoType.valueOf(indicator.getMeteoType().name()))
                .build();
    }

    private static StreamObserver<Empty> getResponseObserver() {
        return new StreamObserver<>() {
            @Override
            public void onNext(Empty empty) {
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onCompleted() {
            }
        };
    }
}