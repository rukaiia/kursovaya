<#macro layout>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
              name="viewport">
        <meta content="ie=edge" http-equiv="X-UA-Compatible">
        <title>Http server Index page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
              integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
              crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
                integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
                crossorigin="anonymous"></script>
        <link rel="stylesheet" href="/static/styles.css">
        <script type="text/javascript" src="/static/js/main.js"></script>
    </head>

    <body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="/">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Books</a>
                </li>
            </ul>

            <#assign
            known = SPRING_SECURITY_CONTEXT??
            >
            <#if known>
                <#assign
                user = SPRING_SECURITY_CONTEXT.authentication.principal
                name = user.getUsername()
                >
                <a href="/profile/${name}" class="text-light m-2 m-sm-0">Hi, ${name}</a>


                <#if _csrf??>
                    <form action="/logout" method="post">
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}"
                               value="${(_csrf.token)!'--no-token--'}"/>
                        <button type="submit" class="btn btn-outline-info m-2" id="logout">Logout</button>
                    </form>
                <#else>
                    <a href="/logout" class="btn btn-outline-info m-2" id="logout">Logout</a>
                </#if>

            <#else>
                <a href="/auth/login" class="btn btn-outline-info m-2">SignIn</a>
                <a href="/auth/register" class="btn btn-outline-info m-2">SignUp</a>
            </#if>
            <#--            <a href="/profile" class="btn btn-outline-success my-2 my-sm-0">Profile</a>-->
            <#--            <#if user?has_content>-->
            <#--                <i class="btn btn-outline-success my-2 my-sm-0">${user.email}</i>-->
            <#--            <#else>-->
            <#--                <a href="/login" class="btn btn-outline-success my-2 my-sm-0">LogIn</a>-->
            <#--            </#if>-->
            <#--            <#if user?has_content>-->
            <#--                <i></i>-->
            <#--            <#else>-->
            <#--                <a href="/register" class="btn btn-outline-success my-2 my-sm-0">Sign up</a>-->
            <#--            </#if>-->
            <#--            <a href="/exit" class="btn btn-outline-success my-2 my-sm-0">Exit</a>-->
        </div>
    </nav>
    <i class="m-5"></i>
    <div class="container">
        <#nested>
    </div>


    <script>
        let logout = document.getElementById('logout')
        if (logout) {
            logout.addEventListener('click', () => localStorage.removeItem('user'))
        }
    </script>
    </body>
    </html>
</#macro>