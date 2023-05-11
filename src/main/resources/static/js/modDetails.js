console.log("test");

function description() {
    var x = document.getElementById("description");
    if (x.style.display === "none") {
        x.style.display = "block";
        document.getElementById("changelog").style.display = "none";
        document.getElementById("files").style.display = "none";
        document.getElementById("news").style.display = "none";
        document.getElementById("user_images").style.display = "none";
        document.getElementById("videos").style.display = "none";
        document.getElementById("comments").style.display = "none";
        document.getElementById("dependecny").style.display = "none";

    }
}

function changelog() {
    var x = document.getElementById("changelog");
    if (x.style.display === "none") {
        x.style.display = "block";
        document.getElementById("description").style.display = "none";
        document.getElementById("files").style.display = "none";
        document.getElementById("news").style.display = "none";
        document.getElementById("user_images").style.display = "none";
        document.getElementById("videos").style.display = "none";
        document.getElementById("comments").style.display = "none";
        document.getElementById("dependecny").style.display = "none";

    }
}

function files() {
    var x = document.getElementById("files");
    if (x.style.display === "none") {
        x.style.display = "block";
        document.getElementById("description").style.display = "none";
        document.getElementById("changelog").style.display = "none";
        document.getElementById("news").style.display = "none";
        document.getElementById("user_images").style.display = "none";
        document.getElementById("videos").style.display = "none";
        document.getElementById("comments").style.display = "none";
        document.getElementById("dependecny").style.display = "none";

    }
}

function news() {
    var x = document.getElementById("news");
    if (x.style.display === "none") {
        x.style.display = "block";
        document.getElementById("description").style.display = "none";
        document.getElementById("changelog").style.display = "none";
        document.getElementById("files").style.display = "none";
        document.getElementById("user_images").style.display = "none";
        document.getElementById("videos").style.display = "none";
        document.getElementById("comments").style.display = "none";
        document.getElementById("dependecny").style.display = "none";

    }
}

function user_images() {
    var x = document.getElementById("user_images");
    if (x.style.display === "none") {
        x.style.display = "block";
        document.getElementById("description").style.display = "none";
        document.getElementById("changelog").style.display = "none";
        document.getElementById("files").style.display = "none";
        document.getElementById("news").style.display = "none";
        document.getElementById("videos").style.display = "none";
        document.getElementById("comments").style.display = "none";
        document.getElementById("dependecny").style.display = "none";

    }
}

function videos() {
    var x = document.getElementById("videos");
    if (x.style.display === "none") {
        x.style.display = "block";
        document.getElementById("description").style.display = "none";
        document.getElementById("changelog").style.display = "none";
        document.getElementById("files").style.display = "none";
        document.getElementById("news").style.display = "none";
        document.getElementById("user_images").style.display = "none";
        document.getElementById("comments").style.display = "none";
        document.getElementById("dependecny").style.display = "none";
    }
}

function comments() {
    var x = document.getElementById("comments");
    if (x.style.display === "none") {
        x.style.display = "block";
        document.getElementById("description").style.display = "none";
        document.getElementById("changelog").style.display = "none";
        document.getElementById("files").style.display = "none";
        document.getElementById("news").style.display = "none";
        document.getElementById("user_images").style.display = "none";
        document.getElementById("videos").style.display = "none";
        document.getElementById("dependecny").style.display = "none";
    }
}

function dependecny() {
    var x = document.getElementById("dependecny");
    if (x.style.display === "none") {
        x.style.display = "block";
        document.getElementById("description").style.display = "none";
        document.getElementById("changelog").style.display = "none";
        document.getElementById("files").style.display = "none";
        document.getElementById("news").style.display = "none";
        document.getElementById("user_images").style.display = "none";
        document.getElementById("comments").style.display = "none";
        document.getElementById("videos").style.display = "none";
    }
}

var comment_popup = document.getElementById("comment_popup");
var changelog_popup = document.getElementById("changelog_popup");
var file_popup = document.getElementById("file_popup");
var video_popup = document.getElementById("video_popup");
var image_popup = document.getElementById("image_popup");
var req_popup = document.getElementById("req_popup");


function show_popup(popup) {

    popup.style.display = "block";

}

function close_popup(popup) {

    popup.style.display = "none";

}

function move() {

    location.href = "../edit_mod.html";

}

function submitVideo() {
    const videoLink = document.getElementById('video_link').value;
    const modId = document.getElementById('mod_id').value;  // Make sure to set this value when the mod details page is loaded

    const video = {
        videoPath: videoLink,
        modId: modId
    };

    fetch('/addVideo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(video)
    }).then(response => {
        if (response.ok) {
            console.log(video)
            alert('Video added successfully!');
            document.getElementById('video_link').value = '';  // Clear the input
        } else {
            alert('Failed to add video. Make sure the URL is valid and try again.');
        }
    });
}

async function submitComment() {
    let modId = document.getElementById("mod_id").value;
    let commentText = document.getElementById("comment_text").value;
    let author = document.getElementById("author").value;

    let comment = {
        modId: modId,
        commentText: commentText,
        userId: author
    };

    const response = await fetch('/api/comments', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(comment),
    });

    if (response.ok) {
        console.log(comment);
        alert("Comment added successfully");
    } else {
        alert("Error adding comment");
    }
}