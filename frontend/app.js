const API_BASE = "http://localhost:8080/api"; // твой backend

$(function() {
    const tokenKey = "jwt_token";

    // Подставляем токен, если он сохранён
    function setToken(token) {
        if (token) {
            localStorage.setItem(tokenKey, token);
            $.ajaxSetup({ headers: { Authorization: "Bearer " + token } });
        } else {
            localStorage.removeItem(tokenKey);
            $.ajaxSetup({ headers: {} });
        }
    }

    setToken(localStorage.getItem(tokenKey));

    // Проверка авторизован ли пользователь
    function checkUser() {
        $.get(API_BASE + "/me")
            .done(user => showUser(user.username))
            .fail(() => showAuthForms());
    }

    // Авторизация
    $("#login-form").submit(function(e) {
        e.preventDefault();
        const data = {
            username: $(this).find("[name=username]").val(),
            password: $(this).find("[name=password]").val()
        };

        $.ajax({
            url: API_BASE + "/auth/login",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(data)
        })
            .done(resp => {
                if (resp.token) {
                    setToken(resp.token);
                    $("#login-msg").text("Успешный вход!");
                    checkUser();
                } else {
                    $("#login-msg").text("Ошибка: токен не получен");
                }
            })
            .fail(xhr => {
                $("#login-msg").text(
                    xhr.status === 401 ? "Неверный логин или пароль" : "Ошибка входа"
                );
            });
    });

    // Регистрация
    $("#register-form").submit(function(e) {
        e.preventDefault();
        const data = {
            username: $(this).find("[name=username]").val(),
            password: $(this).find("[name=password]").val()
        };

        $.ajax({
            url: API_BASE + "/auth/register",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(data)
        })
            .done(() => {
                $("#register-msg").css("color", "green").text("Регистрация успешна! Войдите.");
            })
            .fail(xhr => {
                if (xhr.status === 409)
                    $("#register-msg").text("Пользователь уже существует");
                else $("#register-msg").text("Ошибка регистрации");
            });
    });

    // Выход
    $("#logout-btn").click(() => {
        setToken(null);
        showAuthForms();
    });

    // UI helpers
    function showAuthForms() {
        $("#auth-area").show();
        $("#user-info").hide();
    }

    function showUser(name) {
        $("#auth-area").hide();
        $("#user-info").show();
        $("#username").text(name);
    }

    // При загрузке
    checkUser();
});
