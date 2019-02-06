<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form action="/admin-api/set" method="post">
        <input type="text" name="cityName" placeholder="City name">
        <input type="submit" value="Submit">
    </form>
    <div>
        ${exception}
    </div>
</body>
</html>