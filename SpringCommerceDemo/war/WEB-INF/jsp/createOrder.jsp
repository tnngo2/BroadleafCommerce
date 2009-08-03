<%@ include file="/WEB-INF/jsp/include.jsp" %>
<tiles:insertDefinition name="baseNoSide">
	<tiles:putAttribute name="mainContent" type="string">
	
		<form:form method="post" commandName="salesOrder">
				<br />
				<h4 class="formSectionHeader">Create Order</h4>
				<spring:hasBindErrors name="salesOrder">
					  <spring:bind path="salesOrder.*">
              			<c:forEach var="error" items="${status.errorMessages}">
                			<tr><td><font color="red"><c:out value="${error}"/></font></td></tr><br />
              			</c:forEach>
              		 </spring:bind>
            	</spring:hasBindErrors>

			<table class="formTable">
				<tr>
					<td style="text-align:right"><label for="salesOrderId">Id:</b></label></td>
					<td><input type="text" size="30" class="categoryField" name="id" id="id" value="${salesOrder.id}" /></td>
	    		</tr>
    		</table>
    		<div class="formButtonFooter personFormButtons">
				<input type="submit" class="saveButton" value="Save Changes"/>
			</div>
			</form:form>

	<a href="<c:url value="/logout"/>">Logout</a>
	</tiles:putAttribute>
</tiles:insertDefinition>