window.addEventListener("DOMContentLoaded", () => {
    let alertBox = document.getElementById("alertBox");

    if (alertBox) {
        setTimeout(() => {
            alertBox.style.transition = "opacity 0.5s";
            alertBox.style.opacity = "0";

            setTimeout(() => alertBox.remove(), 500);
        }, 2500);
    }
});