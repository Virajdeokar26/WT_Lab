<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mentee_db";

try {
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    die("Connection failed: " . $e->getMessage());
}

if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['action'])) {
    $full_name = filter_input(INPUT_POST, 'full_name', FILTER_SANITIZE_STRING);
    $email = filter_input(INPUT_POST, 'email', FILTER_SANITIZE_EMAIL);
    $class = filter_input(INPUT_POST, 'class', FILTER_SANITIZE_STRING);
    $mentee_phone = filter_input(INPUT_POST, 'mentee_phone', FILTER_SANITIZE_STRING);
    $parent_phone = filter_input(INPUT_POST, 'parent_phone', FILTER_SANITIZE_STRING);
    $dob = filter_input(INPUT_POST, 'dob', FILTER_SANITIZE_STRING);
    $gender = filter_input(INPUT_POST, 'gender', FILTER_SANITIZE_STRING);
    $address = filter_input(INPUT_POST, 'address', FILTER_SANITIZE_STRING);
    $skills = filter_input(INPUT_POST, 'skills', FILTER_SANITIZE_STRING);
    $hobbies = filter_input(INPUT_POST, 'hobbies', FILTER_SANITIZE_STRING);

    try {
        if ($_POST['action'] == "add") {
            $sql = "INSERT INTO mentees (full_name, email, class, mentee_phone, parent_phone, dob, gender, address, skills, hobbies) 
                    VALUES (:full_name, :email, :class, :mentee_phone, :parent_phone, :dob, :gender, :address, :skills, :hobbies)";
            $stmt = $conn->prepare($sql);
            $stmt->execute([
                ':full_name' => $full_name,
                ':email' => $email,
                ':class' => $class,
                ':mentee_phone' => $mentee_phone,
                ':parent_phone' => $parent_phone,
                ':dob' => $dob,
                ':gender' => $gender,
                ':address' => $address,
                ':skills' => $skills,
                ':hobbies' => $hobbies
            ]);
            echo "<p>Mentee added successfully!</p>";
        }
        // More actions (update, delete) can be added here...
    } catch (PDOException $e) {
        echo "Error: " . $e->getMessage();
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mentee Management System</title>
    <script>
        function validateForm() {
            let phonePattern = /^[0-9]{10}$/;
            let menteePhone = document.querySelector("input[name='mentee_phone']").value;
            let parentPhone = document.querySelector("input[name='parent_phone']").value;

            if (!phonePattern.test(menteePhone)) {
                alert("Please enter a valid 10-digit Mentee Phone No.");
                return false;
            }
            if (!phonePattern.test(parentPhone)) {
                alert("Please enter a valid 10-digit Parent's Phone No.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <h2>Mentee Management System</h2>
    <form action="" method="POST" onsubmit="return validateForm()">
        <label>Full Name:</label>
        <input type="text" name="full_name" required><br>

        <label>Email Address:</label>
        <input type="email" name="email" required><br>

        <label>Class:</label>
        <input type="text" name="class" required><br>

        <label>Mentee Phone No.:</label>
        <input type="tel" name="mentee_phone" required><br>

        <label>Parent's Phone No.:</label>
        <input type="tel" name="parent_phone" required><br>

        <label>Date of Birth:</label>
        <input type="date" name="dob" required><br>

        <label>Gender:</label>
        <input type="radio" name="gender" value="Male" required> Male
        <input type="radio" name="gender" value="Female" required> Female<br>

        <label>Address:</label>
        <textarea name="address" required></textarea><br>

        <label>Skills:</label>
        <input type="text" name="skills" required><br>

        <label>Hobbies:</label>
        <input type="text" name="hobbies" required><br>

        <button type="submit" name="action" value="add">Add</button>
    </form>
</body>
</html>
