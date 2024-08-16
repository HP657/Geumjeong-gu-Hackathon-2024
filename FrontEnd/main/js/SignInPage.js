function signin(event) {
  event.preventDefault();

  const email = document.getElementById("login-email").value;
  const password = document.getElementById("login-password").value;

  const data = {
    email: email,
    password: password,
  };

  fetch("http://localhost:8080/api/auth/signin", {
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
      console.log(data.status);
      if (data.status === 200) {
        alert("로그인 성공! 대시보드로 이동합니다.");
        window.location.href = "./main.html";
      } else {
        alert("로그인 실패: " + data.message);
      }
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("로그인 중 에러가 발생했습니다.");
    });
}
