/**
 * Created by Aleksandr on 11.10.2020.
 */
document.addEventListener("DOMContentLoaded", funcHide);

function funcHide() {
    document.getElementById("topicUpdate").style.display = "none";
    document.getElementById("courseUpdate").style.display = "none";
    document.getElementById("addTopicsForm").style.display = "none"; // прячем форму курса по умолчании
    document.getElementById("createCourseButton").style.display = "block"; // кнопка "Создать форму курса" по умолчании видна
    var validationMessages = document.querySelectorAll('span'); // validationMessages количество спанов (5)
    for(var i = 0; i < validationMessages.length; i++)
    {
        validationMessages[i].style.display = 'none';  // делают не видимыми элементы span
    }
};
function updateCourse() {
    document.getElementById("existedСourse").style.display = "none";
    document.getElementById("courseUpdate").style.display = "block";

    var validationMessages = document.querySelectorAll('span'); // validationMessages количество спанов (5)
    for(var i = 0; i < validationMessages.length; i++)
    {
        validationMessages[i].style.display = 'none';  // делают не видимыми элементы span
    }
};
function submitCourse() {
    var validateMistakesMess = document.querySelectorAll('span'); // все спаны
    var count = 0;
    for (var j = 0; j < validateMistakesMess.length; j++) {
        if (document.forms[1].elements[j].value == 0) {
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
        document.getElementById("saveCourse").type = "submit";
        // Чтобы отправить форму на сервер из JavaScript нужно выполнить метод submit
        document.getElementById("saveCourse").onclick = document.getElementById("saveCourse").submit();
        //
    }
};
function updateTopic() {
    document.getElementById("showTopics").style.display = "none";
    //document.getElementById("topic").style.display = "none";
    document.getElementById("topicUpdate").style.display = "block";
};
function submitTopic() {
    //  id="updateTopicOfCourse"  onclick="submitTopic()" ${topic.getTopicName()}
}
function addTopic() {
    document.getElementById("addTopicsForm").style.display = "block"; // кнопка "Создать форму курса" по умолчании видна
    document.getElementById("button_3").style.display = "none";
    document.getElementById("button_4").style.display = "none";
    document.getElementById("button_5").style.display = "none";
    document.getElementById("button_6").style.display = "none";
    document.getElementById("button_7").style.display = "none";
    document.getElementById("button_8").style.display = "none";
    document.getElementById("button_9").style.display = "none";
    document.getElementById("button_10").style.display = "none";
    document.getElementById("topic_2").style.display = "none";
    document.getElementById("topic_3").style.display = "none";
    document.getElementById("topic_4").style.display = "none";
    document.getElementById("topic_5").style.display = "none";
    document.getElementById("topic_6").style.display = "none";
    document.getElementById("topic_7").style.display = "none";
    document.getElementById("topic_8").style.display = "none";
    document.getElementById("topic_9").style.display = "none";
    document.getElementById("topic_10").style.display = "none";
    // document.getElementById("topic_11").style.display = "none";
    // document.getElementById("topic_12").style.display = "none";
    // document.getElementById("topic_13").style.display = "none
    // document.getElementById("topic_14").style.display = "none";
    // document.getElementById("topic_15").style.display = "none";
    // document.getElementById("topic_16").style.display = "none";
    // document.getElementById("topic_17").style.display = "none";
    // document.getElementById("topic_18").style.display = "none";
    // document.getElementById("topic_19").style.display = "none";
    // document.getElementById("topic_20").style.display = "none";
};
function addTopic2() {
    document.getElementById("topic_2").style.display = "block";
    document.getElementById("button_3").style.display = "block";
};
function addTopic3() {
    document.getElementById("topic_3").style.display = "block";
    document.getElementById("button_4").style.display = "block";
};
function addTopic4() {
    document.getElementById("topic_4").style.display = "block";
    document.getElementById("button_5").style.display = "block";
};
function addTopic5() {
    document.getElementById("topic_5").style.display = "block";
    document.getElementById("button_6").style.display = "block";
};
function addTopic6() {
    document.getElementById("topic_6").style.display = "block";
    document.getElementById("button_7").style.display = "block";
};
function addTopic7() {
    document.getElementById("topic_7").style.display = "block";
    document.getElementById("button_8").style.display = "block";
};
function addTopic8() {
    document.getElementById("topic_8").style.display = "block";
    document.getElementById("button_9").style.display = "block";
};
function addTopic9() {
    document.getElementById("topic_9").style.display = "block";
    document.getElementById("button_10").style.display = "block";
};
function addTopic10() {
    document.getElementById("topic_10").style.display = "block";
    document.getElementById("button_10").style.display = "block";
};
