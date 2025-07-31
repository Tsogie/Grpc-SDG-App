package grpc.generated.chore;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The ChoreDivider service definition. This service contains 2 micro services.
 * rpc doChoreDivide is unary. it gets number of people and returns randomly divided chores.
 * rpc doChoreReport is client streaming. 
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: chore.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ChoreDividerGrpc {

  private ChoreDividerGrpc() {}

  public static final String SERVICE_NAME = "Chore.ChoreDivider";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.generated.chore.ChoreRequest,
      grpc.generated.chore.ChoreResponse> getDoChoderDivideMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "doChoderDivide",
      requestType = grpc.generated.chore.ChoreRequest.class,
      responseType = grpc.generated.chore.ChoreResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.generated.chore.ChoreRequest,
      grpc.generated.chore.ChoreResponse> getDoChoderDivideMethod() {
    io.grpc.MethodDescriptor<grpc.generated.chore.ChoreRequest, grpc.generated.chore.ChoreResponse> getDoChoderDivideMethod;
    if ((getDoChoderDivideMethod = ChoreDividerGrpc.getDoChoderDivideMethod) == null) {
      synchronized (ChoreDividerGrpc.class) {
        if ((getDoChoderDivideMethod = ChoreDividerGrpc.getDoChoderDivideMethod) == null) {
          ChoreDividerGrpc.getDoChoderDivideMethod = getDoChoderDivideMethod =
              io.grpc.MethodDescriptor.<grpc.generated.chore.ChoreRequest, grpc.generated.chore.ChoreResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "doChoderDivide"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.chore.ChoreRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.chore.ChoreResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ChoreDividerMethodDescriptorSupplier("doChoderDivide"))
              .build();
        }
      }
    }
    return getDoChoderDivideMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.generated.chore.ReportRequest,
      grpc.generated.chore.ReportResponse> getDoChoreReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "doChoreReport",
      requestType = grpc.generated.chore.ReportRequest.class,
      responseType = grpc.generated.chore.ReportResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<grpc.generated.chore.ReportRequest,
      grpc.generated.chore.ReportResponse> getDoChoreReportMethod() {
    io.grpc.MethodDescriptor<grpc.generated.chore.ReportRequest, grpc.generated.chore.ReportResponse> getDoChoreReportMethod;
    if ((getDoChoreReportMethod = ChoreDividerGrpc.getDoChoreReportMethod) == null) {
      synchronized (ChoreDividerGrpc.class) {
        if ((getDoChoreReportMethod = ChoreDividerGrpc.getDoChoreReportMethod) == null) {
          ChoreDividerGrpc.getDoChoreReportMethod = getDoChoreReportMethod =
              io.grpc.MethodDescriptor.<grpc.generated.chore.ReportRequest, grpc.generated.chore.ReportResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "doChoreReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.chore.ReportRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.generated.chore.ReportResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ChoreDividerMethodDescriptorSupplier("doChoreReport"))
              .build();
        }
      }
    }
    return getDoChoreReportMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ChoreDividerStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChoreDividerStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChoreDividerStub>() {
        @java.lang.Override
        public ChoreDividerStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChoreDividerStub(channel, callOptions);
        }
      };
    return ChoreDividerStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ChoreDividerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChoreDividerBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChoreDividerBlockingStub>() {
        @java.lang.Override
        public ChoreDividerBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChoreDividerBlockingStub(channel, callOptions);
        }
      };
    return ChoreDividerBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ChoreDividerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ChoreDividerFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ChoreDividerFutureStub>() {
        @java.lang.Override
        public ChoreDividerFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ChoreDividerFutureStub(channel, callOptions);
        }
      };
    return ChoreDividerFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The ChoreDivider service definition. This service contains 2 micro services.
   * rpc doChoreDivide is unary. it gets number of people and returns randomly divided chores.
   * rpc doChoreReport is client streaming. 
   * </pre>
   */
  public static abstract class ChoreDividerImplBase implements io.grpc.BindableService {

    /**
     */
    public void doChoderDivide(grpc.generated.chore.ChoreRequest request,
        io.grpc.stub.StreamObserver<grpc.generated.chore.ChoreResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDoChoderDivideMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.generated.chore.ReportRequest> doChoreReport(
        io.grpc.stub.StreamObserver<grpc.generated.chore.ReportResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getDoChoreReportMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDoChoderDivideMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                grpc.generated.chore.ChoreRequest,
                grpc.generated.chore.ChoreResponse>(
                  this, METHODID_DO_CHODER_DIVIDE)))
          .addMethod(
            getDoChoreReportMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                grpc.generated.chore.ReportRequest,
                grpc.generated.chore.ReportResponse>(
                  this, METHODID_DO_CHORE_REPORT)))
          .build();
    }
  }

  /**
   * <pre>
   * The ChoreDivider service definition. This service contains 2 micro services.
   * rpc doChoreDivide is unary. it gets number of people and returns randomly divided chores.
   * rpc doChoreReport is client streaming. 
   * </pre>
   */
  public static final class ChoreDividerStub extends io.grpc.stub.AbstractAsyncStub<ChoreDividerStub> {
    private ChoreDividerStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChoreDividerStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChoreDividerStub(channel, callOptions);
    }

    /**
     */
    public void doChoderDivide(grpc.generated.chore.ChoreRequest request,
        io.grpc.stub.StreamObserver<grpc.generated.chore.ChoreResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDoChoderDivideMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<grpc.generated.chore.ReportRequest> doChoreReport(
        io.grpc.stub.StreamObserver<grpc.generated.chore.ReportResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getDoChoreReportMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * The ChoreDivider service definition. This service contains 2 micro services.
   * rpc doChoreDivide is unary. it gets number of people and returns randomly divided chores.
   * rpc doChoreReport is client streaming. 
   * </pre>
   */
  public static final class ChoreDividerBlockingStub extends io.grpc.stub.AbstractBlockingStub<ChoreDividerBlockingStub> {
    private ChoreDividerBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChoreDividerBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChoreDividerBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.generated.chore.ChoreResponse doChoderDivide(grpc.generated.chore.ChoreRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDoChoderDivideMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The ChoreDivider service definition. This service contains 2 micro services.
   * rpc doChoreDivide is unary. it gets number of people and returns randomly divided chores.
   * rpc doChoreReport is client streaming. 
   * </pre>
   */
  public static final class ChoreDividerFutureStub extends io.grpc.stub.AbstractFutureStub<ChoreDividerFutureStub> {
    private ChoreDividerFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChoreDividerFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ChoreDividerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.generated.chore.ChoreResponse> doChoderDivide(
        grpc.generated.chore.ChoreRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDoChoderDivideMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DO_CHODER_DIVIDE = 0;
  private static final int METHODID_DO_CHORE_REPORT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ChoreDividerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ChoreDividerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DO_CHODER_DIVIDE:
          serviceImpl.doChoderDivide((grpc.generated.chore.ChoreRequest) request,
              (io.grpc.stub.StreamObserver<grpc.generated.chore.ChoreResponse>) responseObserver);
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
        case METHODID_DO_CHORE_REPORT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.doChoreReport(
              (io.grpc.stub.StreamObserver<grpc.generated.chore.ReportResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ChoreDividerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ChoreDividerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.generated.chore.Chore.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ChoreDivider");
    }
  }

  private static final class ChoreDividerFileDescriptorSupplier
      extends ChoreDividerBaseDescriptorSupplier {
    ChoreDividerFileDescriptorSupplier() {}
  }

  private static final class ChoreDividerMethodDescriptorSupplier
      extends ChoreDividerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ChoreDividerMethodDescriptorSupplier(String methodName) {
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
      synchronized (ChoreDividerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ChoreDividerFileDescriptorSupplier())
              .addMethod(getDoChoderDivideMethod())
              .addMethod(getDoChoreReportMethod())
              .build();
        }
      }
    }
    return result;
  }
}
