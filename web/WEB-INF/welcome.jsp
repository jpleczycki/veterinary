<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vet</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
</head>

<body>

<jsp:include page="fragment/welcomenavbar.jspf" />

<div class="container">
    <div class="row bs-callout bs-callout-primary">

        <div class="col col-md-11 col-sm-10">
            <h3 class="centered"><a href="#">Witamy w klinice weterynaryjnej!</a></h3>
            <h6><small>Dodane przez: Admin, Dnia: 01 styczeń 2016</small></h6>
            <h1>
                <small><bold>Witam na stronie kliniki weterynaryjnej! Zapoznaj się z opisem aplikacji poniżej, załóż konto i zaloguj się aby przejść dalej!</bold></small>
            </h1>
           <h2>
               <BR> <small>1.Możliwość logowania i założenia konta</small>
               <BR> <small>2.Możliwość zgłoszenia problemu ze zwierzakiem. Powinna być możliwość nadania tytułu zgłoszenia, opisu i dodania zdjęcia</small>
               <BR><small>3.Użytkownik może zmienić swoje hasło i dane konta typu : imię, nazwisko, telefon</small>
               <BR><small>4.Użytkownik ma listę wszystkich swoich zgłoszeń na oddzielnej stronie. Powinno tam się wyświetlić datę zgłoszenia i tytuł oraz status zgłoszenia, który jest zmieniamy przez lekarza.</small>
               <BR><small>5.Użytkownik nie powinien widzieć stron dedykowanych dla lekarza</small>
               <BR><small>6.Osoba nie zalogowana nie powinna mieć dostępu do stron osób zajmowanych (lekarz, klient)</small>
               <BR><small>7.Lekarz też może zmienić swoje hasło i dane</small>
               <BR><small>8.Lekarz także widzi listę zgłoszeń, a po wejściu na stronę danego ogłoszenia, widzi tytuł, opis, zdjęcie, a także ma możliwość zmiany jego statusu. Na przykład : zgłoszone, przyjęte, zrealizowane</small>

           </h2>

        </div>

<jsp:include page="fragment/footer.jspf" />

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
</body>
</html>