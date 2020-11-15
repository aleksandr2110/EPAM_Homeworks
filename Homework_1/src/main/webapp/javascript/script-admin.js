document.addEventListener("DOMContentLoaded", funcHide);

function funcHide() {
    document.getElementById("createCourseButton").style.display = "block"; // кнопка "Создать форму курса" по умолчании видна
    document.getElementById("createCourseForm").style.display = "none"; // прячем форму курса по умолчании
    document.getElementById("addTopicsForm").style.display = "none";
    var validationMessages = document.querySelectorAll('span'); // validationMessages количество спанов (5)
    for(var i = 0; i < validationMessages.length; i++)
    {
        validationMessages[i].style.display = 'none';  // делают не видимыми элементы span
    }
}
function sendFormLogin() {
    var key = document.search_login.login;
    var val2 = key.value;
    if((val2.length == 0) || (val2.length < 2))
    {
        alert("Название должно содержать не менее 2 символов");
    }
    else
    {
        document.getElementById("send_login").type = "submit";
        document.getElementById("send_login").onclick = document.getElementById("send_login").submit();
    }
}
function createTeacher() {
    var key = document.assign.teacher;
    var val2 = key.value;
    if((val2.length == 0) || (val2.length < 2))
    {
        alert("Логин должен содержать не менее 2 символов");
    }
    else
    {
        document.getElementById("assign_teacher").type = "submit";
        document.getElementById("assign_teacher").onclick = document.getElementById("assign_teacher").submit();
    }
}
function showCreteCourse() {
    document.getElementById("createCourseButton").style.display = "none"; // кнопка "Создать форму курса" по умолчании видна
    document.getElementById("createCourseForm").style.display = "block"; // прячем форму курса по умолчании
}
function saveCourse()
{
        var validateMistakesMess = document.querySelectorAll('span'); // все спаны
        var count = 0;
        for (var j = 0; j < validateMistakesMess.length; j++) {
            if (document.forms[0].elements[j].value == 0) {
                validateMistakesMess[j].style.display = 'block'; // показываем спан
                count++; // считаем ошибки
            }
            else {
                validateMistakesMess[j].style.display = "none";
            }
        }
        if (count > 0) {
            return;
        }
        else {
            // Чтобы отправить форму на сервер испльзуется submit
            document.getElementById("save_course").type = "submit";
            // Чтобы отправить форму на сервер из JavaScript нужно выполнить метод submit
            document.getElementById("save_course").onclick = document.getElementById("save_course").submit();
        }
}

function openTopic() {
    document.getElementById("open_topic_2").style.display = "none";
    document.getElementById("topic_2").style.display = "block";
}