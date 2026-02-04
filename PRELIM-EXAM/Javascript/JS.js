// Hardcoded CSV data as multi-line string
const csvData = `ID,Name,Grade
1,John Smith,85
2,Emma Johnson,92
3,Michael Williams,78
4,Sophia Brown,95
5,James Davis,88
6,Olivia Miller,91
7,William Wilson,76
8,Ava Moore,89
9,Robert Taylor,82
10,Isabella Anderson,94
11,David Thomas,87
12,Mia Jackson,90
13,Joseph White,79
14,Charlotte Harris,93
15,Charles Martin,84
16,Amelia Thompson,86
17,Thomas Garcia,81
18,Harper Martinez,88
19,Daniel Robinson,92
20,Evelyn Clark,77`;

// Array to store student objects
let students = [];

// Parse CSV data into array of objects
function parseCSV(csvString) {
    const lines = csvString.trim().split('\n');
    const headers = lines[0].split(',');
    const data = [];
    
    for (let i = 1; i < lines.length; i++) {
        const values = lines[i].split(',');
        const obj = {
            id: values[0],
            name: values[1],
            grade: values[2]
        };
        data.push(obj);
    }
    
    return data;
}

// Render function to populate table
function render() {
    const tableBody = document.getElementById('tableBody');
    tableBody.innerHTML = ''; // Clear existing rows
    
    students.forEach((student, index) => {
        const row = `
            <tr>
                <td>${student.id}</td>
                <td>${student.name}</td>
                <td>${student.grade}</td>
                <td>
                    <button class="btn btn-delete" onclick="deleteStudent(${index})">Delete</button>
                </td>
            </tr>
        `;
        tableBody.innerHTML += row;
    });
}

// Add new student
function addStudent(event) {
    event.preventDefault();
    
    const id = document.getElementById('studentID').value;
    const name = document.getElementById('studentName').value;
    const grade = document.getElementById('studentGrade').value;
    
    // Create new student object
    const newStudent = {
        id: id,
        name: name,
        grade: grade
    };
    
    // Push to array
    students.push(newStudent);
    
    // Re-render table
    render();
    
    // Clear form
    document.getElementById('studentForm').reset();
    
    // Show success message
    alert('Student added successfully!');
}

// Delete student
function deleteStudent(index) {
    if (confirm('Are you sure you want to delete this record?')) {
        students.splice(index, 1);
        render();
        alert('Student deleted successfully!');
    }
}

// Initialize on page load
document.addEventListener('DOMContentLoaded', function() {
    // Parse CSV data
    students = parseCSV(csvData);
    
    // Initial render
    render();
    
    // Add form submit listener
    document.getElementById('studentForm').addEventListener('submit', addStudent);
});