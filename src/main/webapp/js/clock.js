const days = ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"];
const months = ["Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
    "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"];

document.addEventListener("DOMContentLoaded", function() {
    updateClock();

    function updateClock() {
        const today = new Date();
        const clockElement = document.getElementById("clock");

        if (clockElement) {
            clockElement.innerHTML = `${today.getDate()} ${months[today.getMonth()]} ${today.getFullYear()}, ${days[today.getDay()]} 
            ${today.getHours()}:${today.getMinutes().toString().padStart(2, '0')}:${today.getSeconds().toString().padStart(2, '0')}`;
        }

        setTimeout(updateClock, 7000);
    }
});