function signup(event) {
  event.preventDefault();

  const email = document.getElementById("signup-email").value;
  const username = document.getElementById("signup-username").value;
  const password = document.getElementById("signup-password").value;
  const confirmPassword = document.getElementById("confirm-password").value;

  if (password !== confirmPassword) {
    document.getElementById("error-message").innerText =
      "비밀번호가 일치하지 않습니다.";
    document.getElementById("error-message").style.display = "block";
    return;
  }
  const data = {
    email: email,
    username: username,
    password: password,
  };

  fetch("http://localhost:8080/api/auth/signup", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
    credentials: "include",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("네트워크 에러");
      }
      return response.json();
    })
    .then((data) => {
      if (data.status === 200) {
        alert("회원가입 성공! 메인페이지로 이동합니다.");
        window.location.href = "./main.html";
      } else {
        alert("회원가입 실패: " + data.message);
      }
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("회원가입 중 에러가 발생했습니다.");
    });
}
