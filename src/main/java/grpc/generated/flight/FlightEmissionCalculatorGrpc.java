package grpc.generated.flight;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: flightEmmission.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FlightEmissionCalculatorGrpc {

  private FlightEmissionCalculatorGrpc() {}

  public static final String SERVICE_NAME = "Flight.FlightEmissionCalculator";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.generated.flight.CO2Request,
      grpc.generated.flight.CO2Response> getDoEmissionCalculationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "doEmissionCalculation",
      requestType = grpc.generated.flight.CO2Request.class,
      responseType = grpc.generated.flight.CO2Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.generated.flight.CO2Request,
      grpc.generated.flight.CO2Response> getDoEmissionCalculationMethod() {
    io.grpc.MethodDescriptor<grpc.generated.flight.CO2Request, grpc.generated.flight.CO2Response> getDoEmissionCalculationMethod;
    if ((getDoEmissionCalculationMethod = FlightEmissionCalculatorGrpc.getDoEmissionCalculationMethod) == null) {
      synchronized (FlightEmissionCalculatorGrpc.class) {
        if ((getDoEmissionCalculationMethod = FlightEmissionCalculatorGrpc.getDoEmissionCalculationMethod) == null) {
          FlightEmissionCalculatorGrpc.getDoEmissionCalculationMethod = getDoEmissionCalculationMethod =
              io.grpc.MethodDescriptor.<grpc.generated.flight.CO2Request, grpc.generated.flight.CO2Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "doEmissionCalculation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.flight.CO2Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.flight.CO2Response.getDefaultInstance()))
              .setSchemaDescriptor(new FlightEmissionCalculatorMethodDescriptorSupplier("doEmissionCalculation"))
              .build();
        }
      }
    }
    return getDoEmissionCalculationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FlightEmissionCalculatorStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FlightEmissionCalculatorStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FlightEmissionCalculatorStub>() {
        @java.lang.Override
        public FlightEmissionCalculatorStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FlightEmissionCalculatorStub(channel, callOptions);
        }
      };
    return FlightEmissionCalculatorStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FlightEmissionCalculatorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FlightEmissionCalculatorBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FlightEmissionCalculatorBlockingStub>() {
        @java.lang.Override
        public FlightEmissionCalculatorBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FlightEmissionCalculatorBlockingStub(channel, callOptions);
        }
      };
    return FlightEmissionCalculatorBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FlightEmissionCalculatorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FlightEmissionCalculatorFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FlightEmissionCalculatorFutureStub>() {
        @java.lang.Override
        public FlightEmissionCalculatorFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FlightEmissionCalculatorFutureStub(channel, callOptions);
        }
      };
    return FlightEmissionCalculatorFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class FlightEmissionCalculatorImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.generated.flight.CO2Request> doEmissionCalculation(
        io.grpc.stub.StreamObserver<grpc.generated.flight.CO2Response> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getDoEmissionCalculationMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDoEmissionCalculationMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                grpc.generated.flight.CO2Request,
                grpc.generated.flight.CO2Response>(
                  this, METHODID_DO_EMISSION_CALCULATION)))
          .build();
    }
  }

  /**
   */
  public static final class FlightEmissionCalculatorStub extends io.grpc.stub.AbstractAsyncStub<FlightEmissionCalculatorStub> {
    private FlightEmissionCalculatorStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FlightEmissionCalculatorStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FlightEmissionCalculatorStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.generated.flight.CO2Request> doEmissionCalculation(
        io.grpc.stub.StreamObserver<grpc.generated.flight.CO2Response> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getDoEmissionCalculationMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class FlightEmissionCalculatorBlockingStub extends io.grpc.stub.AbstractBlockingStub<FlightEmissionCalculatorBlockingStub> {
    private FlightEmissionCalculatorBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FlightEmissionCalculatorBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FlightEmissionCalculatorBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class FlightEmissionCalculatorFutureStub extends io.grpc.stub.AbstractFutureStub<FlightEmissionCalculatorFutureStub> {
    private FlightEmissionCalculatorFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FlightEmissionCalculatorFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FlightEmissionCalculatorFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_DO_EMISSION_CALCULATION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FlightEmissionCalculatorImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FlightEmissionCalculatorImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DO_EMISSION_CALCULATION:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.doEmissionCalculation(
              (io.grpc.stub.StreamObserver<grpc.generated.flight.CO2Response>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FlightEmissionCalculatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FlightEmissionCalculatorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.generated.flight.FlightEmmission.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FlightEmissionCalculator");
    }
  }

  private static final class FlightEmissionCalculatorFileDescriptorSupplier
      extends FlightEmissionCalculatorBaseDescriptorSupplier {
    FlightEmissionCalculatorFileDescriptorSupplier() {}
  }

  private static final class FlightEmissionCalculatorMethodDescriptorSupplier
      extends FlightEmissionCalculatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FlightEmissionCalculatorMethodDescriptorSupplier(String methodName) {
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
      synchronized (FlightEmissionCalculatorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FlightEmissionCalculatorFileDescriptorSupplier())
              .addMethod(getDoEmissionCalculationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
