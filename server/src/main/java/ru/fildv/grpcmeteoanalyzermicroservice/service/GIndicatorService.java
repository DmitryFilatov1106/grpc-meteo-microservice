package ru.fildv.grpcmeteoanalyzermicroservice.service;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.fildv.grpcmeteo.GIndicator;
import ru.fildv.grpcmeteo.MeteoServerGrpc;
import ru.fildv.grpcmeteoanalyzermicroservice.model.Indicator;

@GrpcService
@RequiredArgsConstructor
public class GIndicatorService extends MeteoServerGrpc.MeteoServerImplBase {
    private final IndicatorService indicatorService;

    @Override
    public void addIndicator(final GIndicator gIndicator,
                             final StreamObserver<Empty> observer) {
        save(gIndicator);
        sayCompleted(observer);
    }

    @Override
    public StreamObserver<GIndicator> addStreamOfIndicator(
            final StreamObserver<Empty> observer) {
        return new StreamObserver<>() {
            @Override
            public void onNext(final GIndicator gIndicator) {
                save(gIndicator);
            }

            @Override
            public void onError(final Throwable throwable) {
            }

            @Override
            public void onCompleted() {
                sayCompleted(observer);
            }
        };
    }

    private void save(final GIndicator request) {
        Indicator indicator = new Indicator(request);
        indicatorService.save(indicator);
    }

    private static void sayCompleted(final StreamObserver<Empty> observer) {
        observer.onNext(Empty.newBuilder().build());
        observer.onCompleted();
    }
}
