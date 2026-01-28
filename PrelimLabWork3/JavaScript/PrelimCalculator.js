function calculateScore() {
    // Get input values
    const attendance = parseFloat(document.getElementById('attendance').value);
    const lab1 = parseFloat(document.getElementById('lab1').value);
    const lab2 = parseFloat(document.getElementById('lab2').value);
    const lab3 = parseFloat(document.getElementById('lab3').value);
    
    // Validate inputs
    if (isNaN(attendance) || isNaN(lab1) || isNaN(lab2) || isNaN(lab3)) {
        alert('Please fill in all fields with valid numbers.');
        return;
    }
    
    // Computation Section
    // Lab Work Average = (Lab1 + Lab2 + Lab3) / 3
    const labWorkAverage = (lab1 + lab2 + lab3) / 3;
    
    // Class Standing = (Attendance × 0.40) + (Lab Work Average × 0.60)
    const classStanding = (attendance * 0.40) + (labWorkAverage * 0.60);
    
    // Required Prelim Exam Score Calculation
    // Prelim Grade = (Prelim Exam × 0.70) + (Class Standing × 0.30)
    // Solving for Prelim Exam:
    // Prelim Exam = (Prelim Grade - Class Standing × 0.30) / 0.70
    
    const requiredPrelimForPassing = (75 - (classStanding * 0.30)) / 0.70;
    const requiredPrelimForExcellent = (100 - (classStanding * 0.30)) / 0.70;
    
    // Display computation results
    const computationResults = document.getElementById('computationResults');
    computationResults.innerHTML = `
        <div class="result-item">
            <span class="result-label">Attendance Score:</span>
            <span class="result-value">${attendance.toFixed(0)}</span>
        </div>
        <div class="result-item">
            <span class="result-label">Lab Work 1:</span>
            <span class="result-value">${lab1.toFixed(2)}</span>
        </div>
        <div class="result-item">
            <span class="result-label">Lab Work 2:</span>
            <span class="result-value">${lab2.toFixed(2)}</span>
        </div>
        <div class="result-item">
            <span class="result-label">Lab Work 3:</span>
            <span class="result-value">${lab3.toFixed(2)}</span>
        </div>
        <div class="result-item">
            <span class="result-label">Lab Work Average:</span>
            <span class="result-value">${labWorkAverage.toFixed(2)}</span>
        </div>
        <div class="result-item">
            <span class="result-label">Class Standing:</span>
            <span class="result-value">${classStanding.toFixed(2)}</span>
        </div>
    `;
    
    // Display required scores
    const requiredScores = document.getElementById('requiredScores');
    requiredScores.innerHTML = `
        <div class="score-item">
            <strong>To Pass (75):</strong> ${requiredPrelimForPassing.toFixed(2)}
        </div>
        <div class="score-item">
            <strong>For Excellent (100):</strong> ${requiredPrelimForExcellent.toFixed(2)}
        </div>
    `;
    
    // Generate remarks
    let remarksHTML = '';
    
    // Remarks for passing
    if (requiredPrelimForPassing <= 0) {
        remarksHTML += `
            <div class="remark-box">
                <h3>✅ Passing Status</h3>
                <p>Congratulations! You have already passed the Prelim period based on your current Class Standing.</p>
            </div>
        `;
    } else if (requiredPrelimForPassing > 100) {
        remarksHTML += `
            <div class="remark-box danger">
                <h3>⚠️ Passing Status</h3>
                <p>Unfortunately, it is mathematically impossible to pass the Prelim period even with a perfect exam score. You need to improve your attendance and lab work performance.</p>
            </div>
        `;
    } else {
        remarksHTML += `
            <div class="remark-box warning">
                <h3>📝 Passing Status</h3>
                <p>You need to score at least <strong>${requiredPrelimForPassing.toFixed(2)}</strong> on the Prelim Exam to pass the Prelim period.</p>
            </div>
        `;
    }
    
    // Remarks for excellent
    if (requiredPrelimForExcellent <= 0) {
        remarksHTML += `
            <div class="remark-box">
                <h3>🌟 Excellent Standing</h3>
                <p>You have already achieved an Excellent grade!</p>
            </div>
        `;
    } else if (requiredPrelimForExcellent > 100) {
        remarksHTML += `
            <div class="remark-box warning">
                <h3>🌟 Excellent Standing</h3>
                <p>An Excellent grade (100) is not achievable with the current Class Standing. Keep improving!</p>
            </div>
        `;
    } else {
        remarksHTML += `
            <div class="remark-box">
                <h3>🌟 Excellent Standing</h3>
                <p>You need to score <strong>${requiredPrelimForExcellent.toFixed(2)}</strong> on the Prelim Exam to achieve an Excellent grade.</p>
            </div>
        `;
    }
    
    document.getElementById('remarks').innerHTML = remarksHTML;
    
    // Show results section
    document.getElementById('results').classList.add('show');
    
    // Scroll to results
    document.getElementById('results').scrollIntoView({ behavior: 'smooth', block: 'nearest' });
}

// Allow Enter key to trigger calculation
document.addEventListener('DOMContentLoaded', function() {
    const inputs = document.querySelectorAll('input');
    inputs.forEach(input => {
        input.addEventListener('keypress', function(event) {
            if (event.key === 'Enter') {
                calculateScore();
            }
        });
    });
});