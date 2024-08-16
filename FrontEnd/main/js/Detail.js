let currentUserId = null;

function fetchCurrentUser() {
  return fetch("http://localhost:8080/api/auth/info", {
    method: "GET",
    credentials: "include",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("사용자 정보를 가져오는 데 실패했습니다.");
      }
      return response.json();
    })
    .then((data) => {
      currentUserId = data.data.username;
    })
    .catch((error) => {
      console.error("사용자 정보 가져오기 에러:", error);
    });
}

// 댓글 삭제 기능
function deleteComment(commentId) {
  fetch(`http://localhost:8080/api/comments/del/comment/${commentId}`, {
    method: "DELETE",
    credentials: "include",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("댓글 삭제에 실패했습니다.");
      }
      return response.json();
    })
    .then(() => {
      loadComments();
    })
    .catch((error) => {
      console.error("Error:", error);
    });
}

// 댓글 목록 불러오기
function loadComments() {
  fetch(`http://localhost:8080/api/comments/event/${eventId}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("댓글을 불러오는 데 실패했습니다.");
      }
      return response.json();
    })
    .then((responseData) => {
      const comments = responseData.data;
      const commentsContainer = document.getElementById("comments");
      commentsContainer.innerHTML = "";

      if (comments && comments.length > 0) {
        comments.reverse().forEach((comment) => {
          console.log(comment);
          console.log(currentUserId);
          const commentElement = document.createElement("div");
          commentElement.className = "comment";
          commentElement.innerHTML = `
                      <p>${comment.username} : ${comment.commentText}</p>
                      ${
                        comment.username === currentUserId
                          ? `<button class="delete-comment" data-id="${comment.commentId}">삭제</button>`
                          : ""
                      }
                    `;
          commentsContainer.appendChild(commentElement);
        });

        document.querySelectorAll(".delete-comment").forEach((button) => {
          button.addEventListener("click", function () {
            const commentId = this.getAttribute("data-id");
            deleteComment(commentId);
          });
        });
      } else {
        commentsContainer.textContent = "첫 번째 댓글을 작성해보세요!";
      }
    })
    .catch((error) => {
      console.error("Error:", error);
    });
}

fetchCurrentUser().then(loadComments);

function getQueryParameter(name) {
  const urlParams = new URLSearchParams(window.location.search);
  return urlParams.get(name);
}

const eventId = getQueryParameter("eventId");

const apiUrl = `http://localhost:8080/api/events/${eventId}`;

const eventTitle = document.getElementById("event-title");
const eventImage = document.getElementById("event-image");
const eventPeriod = document.getElementById("event-period");
const eventPlace = document.getElementById("event-place");
const eventContact = document.getElementById("event-contact");
const eventDescription = document.getElementById("event-description");

fetch(apiUrl)
  .then((response) => {
    if (!response.ok) {
      throw new Error("네트워크 응답이 좋지 않습니다.");
    }
    return response.json();
  })
  .then((data) => {
    data = data.data;
    eventTitle.textContent = data.title;
    eventImage.src = data.imgSrc;
    eventPeriod.textContent = data.period;
    eventPlace.textContent = data.place;
    eventContact.textContent = data.contact;
    eventDescription.textContent = data.description;
  })
  .catch((error) => {
    console.error("API 요청 중 오류 발생:", error);
    eventTitle.textContent = "데이터를 가져오는 데 오류가 발생했습니다.";
  });

document
  .getElementById("comment-form")
  .addEventListener("submit", function (event) {
    event.preventDefault();

    const commentText = document.getElementById("comment-text").value;

    const data = {
      eventId: eventId,
      commentText: commentText,
    };

    fetch("http://localhost:8080/api/comments/add/comment", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
      credentials: "include",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("댓글 작성에 실패했습니다.");
        }
        return response.json();
      })
      .then((responseData) => {
        document.getElementById("comment-text").value = "";
        loadComments();
      })
      .catch((error) => {
        console.error("Error:", error);
      });
  });
