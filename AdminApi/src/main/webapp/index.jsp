<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="enter">
    Enter City Name
    <form action="/admin-api/set" method="post">
        <input type="text" name="cityName" placeholder="City name">
        <input type="submit" value="Submit">
    </form>
    <div class="exc">
        ${exception}
    </div>
</div>
</body>
</html>