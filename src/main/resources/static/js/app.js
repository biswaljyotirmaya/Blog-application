window.addEventListener("DOMContentLoaded", () => {
    const alerts = document.querySelectorAll(".alert-banner");

    alerts.forEach((alert, index) => {
        window.setTimeout(() => {
            alert.style.transition = "opacity 0.35s ease, transform 0.35s ease";
            alert.style.opacity = "0";
            alert.style.transform = "translateY(-12px)";

            window.setTimeout(() => alert.remove(), 350);
        }, 2600 + index * 150);
    });
});
