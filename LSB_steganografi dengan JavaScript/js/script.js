function writeIMG() {
  function writefunc() {
    if (
      writeMsgToCanvas("canvas", $("#msg").val(), $("#pass").val(), 0) != null
    ) {
      var myCanvas = document.getElementById("canvas");
      var image = myCanvas.toDataURL("image/png");
      var element = document.createElement("a");
      element.setAttribute("href", image);
      element.setAttribute("download", "result.png");
      element.style.display = "none";
      document.body.appendChild(element);
      element.click();
      document.body.removeChild(element);
    }
  }
  loadIMGtoCanvas("file", "canvas", writefunc, 500);
}

function readIMG() {
  function readfunc() {
    var t = readMsgFromCanvas("canvas", $("#pass1").val(), 0);
    if (t != null) {
      t = t.split("&").join("&amp;");
      t = t.split(" ").join("&nbsp;");
      t = t.split("<").join("&lt;");
      t = t.split(">").join("&gt;");
      t = t.replace(/(?:\r\n|\r|\n)/g, "<br />");
      $("#result").html(t);
    } else $("#result").html("Kesalahan saat ekstrak pesan");
  }
  loadIMGtoCanvas("file1", "canvas", readfunc);
}

$(document).ready(function() {
  $(".content").html(`
        <div class="row justify-content-center mt-4">
        <div class="col-10">
          <div class="form-group">
            <label for="msg">Message : </label>
            <textarea
              class="form-control"
              rows="3"
              id="msg"
              required
            ></textarea>
          </div>
          <div class="form-group">
            <label for="pass">Key : </label>
            <input type="text" class="form-control" id="pass" required />
          </div>
          <div class="form-group">
            <label for="file">Cover image : </label>
            <input type="file" class="form-control-file" id="file" accept="image/*" />
          </div>
          <button type="button" class="btn btn-primary" onclick="writeIMG()">Embed Message</button>
        </div>
      </div>
        `);

  $(".btn-embed").click(function() {
    $(this).addClass("active-button");
    $(".btn-extract").removeClass("active-button");
    $(".nav-embed").addClass("active");
    $(".nav-extract").removeClass("active");
    $(".content").html(`
      <div class="row justify-content-center mt-4">
      <div class="col-10">
        <div class="form-group">
          <label for="msg">Message : </label>
          <textarea
            class="form-control"
            rows="3"
            id="msg"
            required
          ></textarea>
        </div>
        <div class="form-group">
          <label for="pass">Key : </label>
          <input type="text" class="form-control" id="pass" required />
        </div>
        <div class="form-group">
          <label for="file">Cover image : </label>
          <input type="file" class="form-control-file" id="file" accept="image/*" />
        </div>
        <button type="button" class="btn btn-primary" onclick="writeIMG()">Embed Message</button>
      </div>
    </div>
    `);
  });

  $(".btn-extract").click(function() {
    $(this).addClass("active-button");
    $(".btn-embed").removeClass("active-button");
    $(".nav-extract").addClass("active");
    $(".nav-embed").removeClass("active");
    $(".content").html(`
        <div class="row justify-content-center">
        <div class="col-10 mt-4">
          <div class="form-group">
            <label for="file1">Cover image : </label>
            <input type="file" class="form-control-file" id="file1" />
          </div>
          <div class="form-group">
            <label for="pass1">Key :</label>
            <input type="text" class="form-control" id="pass1" required />
          </div>
          <div class="form-group">
            <label for="result">Fill in the message :</label>
            <textarea
              class="form-control"
              rows="3"
              id="result"
              required
            ></textarea>
          </div>
          <button type="button" class="btn btn-primary" onclick="readIMG()">Extract Message</button>
        </div>
      </div>
        `);
  });
});
