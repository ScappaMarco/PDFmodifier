function togglePassword() {
    const passwordField = document.getElementById("password");
    const confirmPasswordField = document.getElementById("confirmPassword");
    const icon = document.getElementById("toggleIcon");

    if (passwordField.type === "password") {
        confirmPasswordField.type = "text";
        passwordField.type = "text";
        icon.classList.remove("bi-eye-slash");
        icon.classList.add("bi-eye");
    } else {
        passwordField.type = "password";
        confirmPasswordField.type = "password";
        icon.classList.remove("bi-eye");
        icon.classList.add("bi-eye-slash");
    }
}