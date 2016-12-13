/*
 * Copyright 2016, Google Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *    * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *
 *    * Neither the name of Google Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package io.grpc.netty;

import io.grpc.Internal;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.netty.NettyChannelBuilder.NettyTransportFactory;

import java.net.SocketAddress;

/**
 * Internal {@link NettyChannelBuilder} accessor.  This is intended for usage internal to the gRPC
 * team.  If you *really* think you need to use this, contact the gRPC team first.
 */
@Internal
public final class InternalNettyChannelBuilder {

  /**
   * Checks authority upon channel construction.  The purpose of this interface is to raise the
   * visibility of {@link NettyChannelBuilder.OverrideAuthorityChecker}.
   */
  public interface OverrideAuthorityChecker extends NettyChannelBuilder.OverrideAuthorityChecker {}

  public static void overrideAuthorityChecker(
      NettyChannelBuilder channelBuilder, OverrideAuthorityChecker authorityChecker) {
    channelBuilder.overrideAuthorityChecker(authorityChecker);
  }

  /**
   * Creates a custom client transport that allows overriding the protocol negotiator.
   */
  public static ConnectionClientTransport newClientTransport(
      ClientTransportFactory transportFactory, SocketAddress serverAddress, String authority,
      String userAgent, ProtocolNegotiator negotiator) {
    // Casting to avoid making {@link NettyTransportFactory} public.
    return ((NettyTransportFactory) transportFactory)
        .newClientTransport(serverAddress, authority, userAgent, negotiator);
  }

  private InternalNettyChannelBuilder() {}
}
