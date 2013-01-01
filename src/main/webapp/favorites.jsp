<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<f:view>
    <html>
    <head>
        <title>success</title>
        <link rel="stylesheet" type="text/css" href="reference.css"/>
    </head>
    <body>
    <h1>Hello <h:outputText value="#{loginBean.username}"/></h1>

    <p>Your favorites are:</p>
    <h:dataTable value="#{userBean.favorites}" var="favorite" border="1">
        <h:column>
            <h:outputText value="#{favorite}"/>
        </h:column>
    </h:dataTable>
    <h:form>
        <h:inputText value="#{userBean.newFavorite}"/>
        <h:commandButton value="add favorite" action="#{userBean.addFavorite}"/>
        <h:commandButton value="logout" action="#{userBean.logout}"/>
    </h:form>
    </body>
    </html>
</f:view>