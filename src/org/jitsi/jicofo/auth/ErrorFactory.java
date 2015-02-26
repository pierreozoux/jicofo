/*
 * Jicofo, the Jitsi Conference Focus.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package org.jitsi.jicofo.auth;

import org.jitsi.impl.protocol.xmpp.extensions.*;
import org.jivesoftware.smack.packet.*;

/**
 * Utility class for creating XMPP error responses.
 *
 * @author Pawel Domas
 */
public class ErrorFactory
{
    /**
     * Creates 'not-authorized' XMPP error response to given <tt>query</tt>.
     *
     * @param query the IQ for which error response wil be created.
     *
     * @return XMPP 'not-authorized' error response to given <tt>query</tt>.
     */
    public static IQ createNotAuthorizedError(IQ query)
    {
        final XMPPError error
            = new XMPPError(XMPPError.Condition.not_authorized);

        return IQ.createErrorResponse(query, error);
    }

    /**
     * Creates 'not-acceptable' XMPP error response with application specific
     * 'session-invalid' error extension.
     *
     * @param query the IQ for which error response will be created.
     *
     * @return XMPP 'not-acceptable' error response to given <tt>query</tt>
     *         with application specific 'session-invalid' extension.
     */
    public static IQ createSessionInvalidResponse(IQ query)
    {
        final XMPPError error
            = new XMPPError(
                    XMPPError.Condition.no_acceptable, "invalid session");

        // session-invalid application specific error
        error.addExtension(new SessionInvalidPacketExtension());

        return IQ.createErrorResponse(query, error);
    }

    /**
     * Creates 'not-acceptable' XMPP error response to given <tt>query</tt>.
     *
     * @param query the IQ for which error response will be created.
     *
     * @param errorMessage application specific error message included in
     *                     error response.
     *
     * @return 'not-acceptable' XMPP error response to given <tt>query</tt> with
     *          included <tt>errorMessage</tt>.
     */
    public static IQ createNotAcceptableError(IQ query, String errorMessage)
    {
        // not acceptable
        final XMPPError error
            = new XMPPError(XMPPError.Condition.no_acceptable, errorMessage);

        return IQ.createErrorResponse(query, error);
    }
}