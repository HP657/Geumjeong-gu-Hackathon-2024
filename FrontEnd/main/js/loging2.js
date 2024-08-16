fetch("http://localhost:8080/api/auth/info", {
  method: "GET",
  credentials: "include",
})
  .then((response) => {
    if (!response.ok) {
      throw new Error("Not authenticated");
    }
    return response.json();
  })
  .then((data) => {
    console.log(data);
    const authStatus = document.getElementById("loging");
    authStatus.textContent = `${data.data.username} 로그인 중`;
    const logouting = document.getElementById("logout");
    logouting.textContent = "로그아웃";
  })
  .catch((error) => {
    console.error("Error:", error);
  });
