<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<f:view>
    <html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="reference.css"/>
    </head>
    <body>
    <h3><h:message for="form"/></h3>

    <h1>Welcome to this webapplication</h1>
    <h:form id="form">
        <table>
            <tr>
                <td><h:inputText id="username" value="#{loginBean.username}"/></td>
            </tr>
            <tr>
                <td><h:inputSecret id="password" value="#{loginBean.password}"/></td>
            </tr>
            <tr>
                <td colspan="2"><h:commandButton value="login" action="#{loginBean.login}"/></td>
            </tr>
        </table>
    </h:form>

    </body>
    </html>
</f:view>