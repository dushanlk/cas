<jsp:directive.include file="includes/top.jsp"/>
<div id="msg" class="info">
    <h4>Select your account</h4><hr/>
    <ul>
        <c:forEach items="${linkedAccounts}" var="message">
            <li>
                <form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">
                    <input type="hidden" id="initialAuthToken" name="initialAuthToken" value="<c:out value="${message.initialAuthToken}" />" />
                    <input type="hidden" id="linkedUsername" name="linkedUsername" value="<c:out value="${message.linkedUsername}" />" />

                    <input type="hidden" name="execution" value="${flowExecutionKey}" />
                    <input type="hidden" name="_eventId" value="submit" />

                    <input class="btn-submit" name="submit" accesskey="l" value="<c:out value="${message.linkedUsername}" />" tabindex="6" type="submit" />
                </form:form>
            </li>
        </c:forEach>
    </ul>
</div>
<jsp:directive.include file="includes/bottom.jsp"/>