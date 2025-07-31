package grpc.generated.monitoring;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: monitoring.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class StoreMonitoringServiceGrpc {

  private StoreMonitoringServiceGrpc() {}

  public static final String SERVICE_NAME = "Monitoring.StoreMonitoringService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.generated.monitoring.MonitoringRequest,
      grpc.generated.monitoring.MonitoringResponse> getDoMonitoringMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "doMonitoring",
      requestType = grpc.generated.monitoring.MonitoringRequest.class,
      responseType = grpc.generated.monitoring.MonitoringResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.generated.monitoring.MonitoringRequest,
      grpc.generated.monitoring.MonitoringResponse> getDoMonitoringMethod() {
    io.grpc.MethodDescriptor<grpc.generated.monitoring.MonitoringRequest, grpc.generated.monitoring.MonitoringResponse> getDoMonitoringMethod;
    if ((getDoMonitoringMethod = StoreMonitoringServiceGrpc.getDoMonitoringMethod) == null) {
      synchronized (StoreMonitoringServiceGrpc.class) {
        if ((getDoMonitoringMethod = StoreMonitoringServiceGrpc.getDoMonitoringMethod) == null) {
          StoreMonitoringServiceGrpc.getDoMonitoringMethod = getDoMonitoringMethod =
              io.grpc.MethodDescriptor.<grpc.generated.monitoring.MonitoringRequest, grpc.generated.monitoring.MonitoringResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "doMonitoring"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.monitoring.MonitoringRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.monitoring.MonitoringResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StoreMonitoringServiceMethodDescriptorSupplier("doMonitoring"))
              .build();
        }
      }
    }
    return getDoMonitoringMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StoreMonitoringServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StoreMonitoringServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StoreMonitoringServiceStub>() {
        @java.lang.Override
        public StoreMonitoringServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StoreMonitoringServiceStub(channel, callOptions);
        }
      };
    return StoreMonitoringServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StoreMonitoringServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StoreMonitoringServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StoreMonitoringServiceBlockingStub>() {
        @java.lang.Override
        public StoreMonitoringServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StoreMonitoringServiceBlockingStub(channel, callOptions);
        }
      };
    return StoreMonitoringServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StoreMonitoringServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StoreMonitoringServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StoreMonitoringServiceFutureStub>() {
        @java.lang.Override
        public StoreMonitoringServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StoreMonitoringServiceFutureStub(channel, callOptions);
        }
      };
    return StoreMonitoringServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class StoreMonitoringServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void doMonitoring(grpc.generated.monitoring.MonitoringRequest request,
        io.grpc.stub.StreamObserver<grpc.generated.monitoring.MonitoringResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDoMonitoringMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDoMonitoringMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                grpc.generated.monitoring.MonitoringRequest,
                grpc.generated.monitoring.MonitoringResponse>(
                  this, METHODID_DO_MONITORING)))
          .build();
    }
  }

  /**
   */
  public static final class StoreMonitoringServiceStub extends io.grpc.stub.AbstractAsyncStub<StoreMonitoringServiceStub> {
    private StoreMonitoringServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StoreMonitoringServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StoreMonitoringServiceStub(channel, callOptions);
    }

    /**
     */
    public void doMonitoring(grpc.generated.monitoring.MonitoringRequest request,
        io.grpc.stub.StreamObserver<grpc.generated.monitoring.MonitoringResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getDoMonitoringMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class StoreMonitoringServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<StoreMonitoringServiceBlockingStub> {
    private StoreMonitoringServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StoreMonitoringServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StoreMonitoringServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<grpc.generated.monitoring.MonitoringResponse> doMonitoring(
        grpc.generated.monitoring.MonitoringRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getDoMonitoringMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class StoreMonitoringServiceFutureStub extends io.grpc.stub.AbstractFutureStub<StoreMonitoringServiceFutureStub> {
    private StoreMonitoringServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StoreMonitoringServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StoreMonitoringServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_DO_MONITORING = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StoreMonitoringServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StoreMonitoringServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DO_MONITORING:
          serviceImpl.doMonitoring((grpc.generated.monitoring.MonitoringRequest) request,
              (io.grpc.stub.StreamObserver<grpc.generated.monitoring.MonitoringResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StoreMonitoringServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StoreMonitoringServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.generated.monitoring.Monitoring.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StoreMonitoringService");
    }
  }

  private static final class StoreMonitoringServiceFileDescriptorSupplier
      extends StoreMonitoringServiceBaseDescriptorSupplier {
    StoreMonitoringServiceFileDescriptorSupplier() {}
  }

  private static final class StoreMonitoringServiceMethodDescriptorSupplier
      extends StoreMonitoringServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StoreMonitoringServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StoreMonitoringServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StoreMonitoringServiceFileDescriptorSupplier())
              .addMethod(getDoMonitoringMethod())
              .build();
        }
      }
    }
    return result;
  }
}
