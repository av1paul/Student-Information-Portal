<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Student</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>
    <header class="navbar">
        <div class="navbar__brand">
            <div class="navbar__brand-heading">Paul University</div>
            <div class="navbar__brand-subheading">Add Student</div>
        </div>
    </header>

    <div class="main-form">
        <form action="StudentControllerServlet" class="form" method="GET">
            <input type="hidden" name="command" value="ADD">
            <div class="form__input">
            	<label for="form__firstname" class="form__label">Firstname:</label>
                <input type="text" id="form__firstname" class="form__field" name="firstName" required>
            </div>
            <div class="form__input">
            	<label for="form__firstname" class="form__label">Lastname:</label>
                <input type="text" id="form__lastname" class="form__field" name="lastName" required>
            </div>
            <div class="form__input">
            	<label for="form__firstname" class="form__label">Email:</label>
                <input type="email" id="form__email" class="form__field" name="email" required>
            </div>
            <div class="form__submit">
                <input type="submit" value="Add" class="save-student-button" />
            </div>
        </form>
        <input type="submit" value="Student Information Portal"
            onclick="window.location.href='StudentControllerServlet'; return false;" class="back-student-button" />
    </div>

    <footer class="footer">
        <div class="footer__content">
            <section class="left">
                <a href="https://www.linkedin.com/in/avishek-paul-b275521b5/" class="footer__item">Made by <span
                        class="nextline">Avishek Paul</span></a>
            </section>
            <section class="right">
                <a href="https://github.com/av1paul" class="footer__item">Check out my <span class="nextline">other
                        projects</span></a>
            </section>
        </div>
    </footer>

</body>

</html>