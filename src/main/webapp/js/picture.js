let picture = document.getElementById("picture");
let context = picture.getContext('2d');
let height = picture.height / 2;
let width = picture.width / 2;
let scaleR = width * 0.9
let arrow = {width: 4, length: 10};
let padding = {xPad: 12, yPad: 13}
let dotRadius = 5;
let strokeLen = 4;
let missColor = 'rgb(255,0,0)';
let hitColor = 'rgb(0,255,34)';
let notSentColor = 'rgb(83,83,83)'
let R = document.getElementById("inputForm:rValue").value;

drawAxis();
drawArrows();
drawCoords();
drawRect();
drawTriangle();
drawCircle();

function drawBackground() {
    context.clearRect(0, 0, picture.width, picture.height);
    drawAxis();
    drawArrows();
    drawCoords();
    drawRect();
    drawTriangle();
    drawCircle();
}

function updateR() {
    if (document.getElementById("inputForm:rValue").value === R) return false;
    else {
        R = document.getElementById("inputForm:rValue").value;
        return true;
    }
}

function drawAxis() {
    context.strokeStyle = 'black';
    context.lineWidth = 2;
    context.beginPath();
    context.moveTo(0, height);
    context.lineTo(width * 2, height);
    context.stroke();
    context.beginPath();
    context.moveTo(width, 0);
    context.lineTo(width, height * 2);
    context.stroke();
}

function drawArrows() {
    context.strokeStyle = 'black';
    context.lineWidth = 2;
    context.beginPath();
    context.moveTo(width - arrow.width, arrow.length);
    context.lineTo(width, 0);
    context.lineTo(width + arrow.width, arrow.length)
    context.stroke();
    context.beginPath();
    context.moveTo(width * 2 - arrow.length, height - arrow.width);
    context.lineTo(width * 2, height);
    context.lineTo(width * 2 - arrow.length, height + arrow.width);
    context.stroke();
}

function drawCoords() {
    context.strokeStyle = 'black';
    context.fillStyle = 'black'
    context.beginPath();
    context.fillText("0", width - padding.xPad, height + padding.yPad);
    context.stroke();

    context.beginPath();
    context.moveTo(width + scaleR, height - strokeLen);
    context.lineTo(width + scaleR, height + strokeLen);
    context.stroke();
    context.beginPath();
    context.fillText("R", width + scaleR - padding.xPad, height + padding.yPad);
    context.stroke();

    context.beginPath();
    context.moveTo(width + scaleR / 2, height - strokeLen);
    context.lineTo(width + scaleR / 2, height + strokeLen);
    context.stroke();
    context.beginPath();
    context.fillText("R/2", width + scaleR / 2 - padding.xPad, height + padding.yPad);
    context.stroke();

    context.beginPath();
    context.moveTo(width - scaleR, height - strokeLen);
    context.lineTo(width - scaleR, height + strokeLen);
    context.stroke();
    context.beginPath();
    context.fillText("-R", width - scaleR - padding.xPad, height + padding.yPad);
    context.stroke();

    context.beginPath();
    context.moveTo(width - scaleR / 2, height - strokeLen);
    context.lineTo(width - scaleR / 2, height + strokeLen);
    context.stroke();
    context.beginPath();
    context.fillText("-R/2", width - scaleR / 2 - padding.xPad, height + padding.yPad);
    context.stroke();

    context.beginPath();
    context.moveTo(width - strokeLen, height - scaleR);
    context.lineTo(width + strokeLen, height - scaleR);
    context.stroke();
    context.beginPath();
    context.fillText("R", width - padding.xPad, height - scaleR + padding.yPad);
    context.stroke();

    context.beginPath();
    context.moveTo(width - strokeLen, height - scaleR / 2);
    context.lineTo(width + strokeLen, height - scaleR / 2);
    context.stroke();
    context.beginPath();
    context.fillText("R/2", width - padding.xPad, height - scaleR / 2 + padding.yPad);
    context.stroke();

    context.beginPath();
    context.moveTo(width - strokeLen, height + scaleR);
    context.lineTo(width + strokeLen, height + scaleR);
    context.stroke();
    context.beginPath();
    context.fillText("-R", width - padding.xPad, height + scaleR + padding.yPad);
    context.stroke();

    context.beginPath();
    context.moveTo(width - strokeLen, height + scaleR / 2);
    context.lineTo(width + strokeLen, height + scaleR / 2);
    context.stroke();
    context.beginPath();
    context.fillText("-R/2", width - padding.xPad, height + scaleR / 2 + padding.yPad);
    context.stroke();
}

function drawRect() {
    context.strokeStyle = 'rgba(1,51,213,0.7)';
    context.fillStyle = 'rgba(24,109,255,0.4)';
    context.strokeRect(width - scaleR, height, scaleR, scaleR);
    context.fillRect(width - scaleR, height, scaleR, scaleR);
}

function drawTriangle() {
    context.strokeStyle = 'rgba(1,51,213,0.7)';
    context.fillStyle = 'rgba(24,109,255,0.4)';
    context.beginPath();
    context.moveTo(width, height);
    context.lineTo(width + scaleR, height);
    context.lineTo(width, height + scaleR);
    context.stroke();
    context.fill();
}

function drawCircle() {
    context.strokeStyle = 'rgba(1,51,213,0.7)';
    context.fillStyle = 'rgba(24,109,255,0.4)';
    context.beginPath();
    context.moveTo(width, height);
    context.lineTo(width, height - scaleR/2);
    context.arc(width, height, scaleR/2, -Math.PI / 2, Math.PI, 1)
    context.moveTo(width, height);
    context.stroke();
    context.fill();
}

function drawPoint(x, y, color) {
    context.strokeStyle = color;
    context.fillStyle = color;
    let xCoord = xCoordToCanv(x);
    let yCoord = yCoordToCanv(y);
    context.beginPath();
    context.arc(xCoord, yCoord, dotRadius, 0, 2 * Math.PI);
    context.stroke();
    context.fill();
}

function xCoordToCanv(x) {
    updateR();
    return width + x / R * scaleR;
}

function yCoordToCanv(y) {
    updateR();
    return height - y / R * scaleR;
}

function drawPoints() {
    drawBackground();
    let table = document.getElementById("resultTable");
    let rows = table.getElementsByTagName('tr');
    //console.log(rows)
    for (let i = 0; i < rows.length; i++) {
        console.log(rows[i]);
        let cells = rows[i].getElementsByTagName('td');
        updateR();
        if (cells[0] != null && cells[1] != null && R !== 0) {
            let x = cells[0].innerText;
            let y = cells[1].innerText;
            if (y === "") {
                continue;
            }
            if (cells[3].innerText === 'Hit') {
                drawPoint(x, y, hitColor);
            } else {
                drawPoint(x, y, missColor);
            }
        }
    }
}


picture.addEventListener("click", function (click) {
    updateR();
    if(R!==0) {
        let mouseX = parseFloat(click.clientX);
        let mouseY = parseFloat(click.clientY);
        let graphRect = picture.getBoundingClientRect();
        let seX = (mouseX - graphRect.left - picture.width / 2) / scaleR * R;
        let seY = -(mouseY - graphRect.top - picture.height / 2) / scaleR * R;

        document.getElementById("inputForm:x_value").value = seX;
        document.getElementById("inputForm:yValue").value = seY;

        document.getElementById("inputForm:x_value").dispatchEvent(new Event('change'));
        document.getElementById("inputForm:yValue").dispatchEvent(new Event('change'));

        let count1 = 0;
        let count2 = 0;
        document.getElementById("inputForm:submit_button").dispatchEvent(new Event('click'));

        faces.ajax.addOnEvent(function (data) {
            drawPoints();
        });

        drawPoints();
    }
    else {
        writeMessage("выберите R");
    }
});

function writeMessage(str) {
    document.getElementById("message").innerText = str;
}