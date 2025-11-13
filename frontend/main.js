const API_URL = "http://localhost:8080/api/auth";

// ===== ЛОГИН =====
$("#loginForm").submit(function(event) {
    event.preventDefault();
    const data = {
        username: $("#username").val(),
        password: $("#password").val()
    };

    $.ajax({
        url: API_URL + "/login",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function(resp) {
            if(resp.token) {
                localStorage.setItem("token", resp.token);
                $("#message").css("color", "green").text("Успешный вход!");
                setTimeout(() => window.location.href = "logout.html", 1000);
            } else {
                $("#message").css("color", "red").text("Ошибка входа: токен не найден");
            }
        },
        error: function(xhr) {
            let errMsg = "Ошибка входа";
            try { errMsg = JSON.parse(xhr.responseText).message || errMsg; } catch(e){}
            $("#message").css("color", "red").text(errMsg);
        }
    });
});

// ===== РЕГИСТРАЦИЯ =====
$("#registerForm").submit(function(event) {
    event.preventDefault();
    const data = {
        username: $("#regUsername").val(),
        email: $("#regEmail").val(),
        password: $("#regPassword").val(),
        confirmPassword: $("#regConfirmPassword").val()
    };

    $.ajax({
        url: API_URL + "/registration",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function() {
            $("#message").css("color", "green").text("Регистрация успешна! Перейдите на вход.");
            setTimeout(() => window.location.href = "index.html", 1500);
        },
        error: function(xhr) {
            let errMsg = "Ошибка регистрации";
            try { errMsg = JSON.parse(xhr.responseText).message || errMsg; } catch(e){}
            $("#message").css("color", "red").text(errMsg);
        }
    });
});

// ===== ВЫХОД =====
$("#logoutBtn").click(function() {
    localStorage.removeItem("token");
    window.location.href = "index.html";
});