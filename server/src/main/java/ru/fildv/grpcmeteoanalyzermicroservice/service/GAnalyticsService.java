package ru.fildv.grpcmeteoanalyzermicroservice.service;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.fildv.grpcmeteo.AnalyticsServerGrpc;
import ru.fildv.grpcmeteo.GAnalyticsRequest;
import ru.fildv.grpcmeteo.GIndicator;
import ru.fildv.grpcmeteo.GMeteoType;
import ru.fildv.grpcmeteoanalyzermicroservice.model.Indicator;

import java.time.ZoneOffset;
import java.util.List;

@GrpcService
@Slf4j
@RequiredArgsConstructor
public class GAnalyticsService
        extends AnalyticsServerGrpc.AnalyticsServerImplBase {
    private final IndicatorService indicatorService;

    @Override
    public void getIndicators(final GAnalyticsRequest request,
                              final StreamObserver<GIndicator> observer) {
        List<Indicator> indicators
                = indicatorService.get(request.getBatchSize());
        for (Indicator indicator : indicators) {
            GIndicator gIndicator = GIndicator.newBuilder()
                    .setMeteoId(indicator.getMeteoId())
                    .setTimestamp(
                            Timestamp.newBuilder()
                                    .setSeconds(
                                            indicator.getTimestamp()
                                                    .toEpochSecond(
                                                            ZoneOffset.UTC)
                                    )
                                    .build())
                    .setValue(indicator.getValue())
                    .setMeteoType(
                            GMeteoType.valueOf(
                                    indicator.getMeteoType().name()))
                    .build();
            observer.onNext(gIndicator);
        }
        log.info("Work completed. Sent {}", request.getBatchSize());
        observer.onCompleted();
    }
}
