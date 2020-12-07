 document.addEventListener( "DOMContentLoaded", hideFormAndSaveButtonOnLoad); // метод может вмещать несколько функий-обработчикв
// window.onload = hideFormAndSaveButtonOnLoad;


function hideFormAndSaveButtonOnLoad()
{
var validationMessages = document.querySelectorAll('span'); // validationMessages количество спанов (3)
for(var i = 0; i < validationMessages.length; i++)
{
   validationMessages[i].style.display = 'none';  // делают не видимыми элементы span
}
};
// Конец блока DOM

function BeforeSave()
{
var validateMistakesMess = document.querySelectorAll('span'); // все спаны
var count = 0;
for(var j = 0; j < validateMistakesMess.length; j++)
  {
   if(document.forms[0].elements[j].value == 0)
    {
      validateMistakesMess[j].style.display = 'block'; // показываем спан
      count++; // считаем ошибки
    }
    else
   {
       validateMistakesMess[j].style.display = "none";
   }
  }
  if(count>0)
    {
    return;
    }
    else
    {
    // Чтобы отправить форму на сервер испльзуется submit
    document.getElementById("Save").type = "submit";
    // Чтобы отправить форму на сервер из JavaScript нужно выполнить метод submit
    document.getElementById("Save").onclick = document.getElementById("Save").submit();
     //
    }
 }

