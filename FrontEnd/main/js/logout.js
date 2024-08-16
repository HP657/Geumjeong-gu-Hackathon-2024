function logout() {
  fetch("http://localhost:8080/api/auth/logout", {
    method: "POST",
    credentials: "include", // 쿠키나 세션 정보를 포함하여 요청
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Logout failed");
      }
      return response.json();
    })
    .then((data) => {
      console.log("Logout successful:", data);
      // 로그아웃이 성공하면 로그인 페이지로 리디렉션
      window.location.href = "./../main/SignInPage.html";
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("로그아웃 중 오류가 발생했습니다.");
    });
}
