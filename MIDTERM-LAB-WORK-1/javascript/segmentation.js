const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function getFilePath() {
    rl.question('Enter dataset file path: ', (path) => {
        // 1. Validation
        if (fs.existsSync(path) && path.endsWith('.csv')) {
            processFile(path);
        } else {
            console.log("Error: File not found or not a valid CSV.");
            getFilePath(); // Loop
        }
    });
}

// Modular Design: Function for processing
function processFile(path) {
    const data = fs.readFileSync(path, 'utf8');
    const lines = data.split('\n').slice(1); // Skip header

    const segments = {
        Platinum: [],
        Gold: [],
        Silver: [],
        Bronze: []
    };

    lines.forEach(line => {
        if (!line.trim()) return;
        const [name, revenueStr] = line.split(',');
        const revenue = parseFloat(revenueStr);

        // 2. Segmentation Logic
        if (revenue > 100000) segments.Platinum.push(name);
        else if (revenue >= 50000) segments.Gold.push(name);
        else if (revenue >= 10000) segments.Silver.push(name);
        else segments.Bronze.push(name);
    });

    // 3. Output
    Object.keys(segments).forEach(tier => {
        console.log(`\n--- ${tier} (${segments[tier].length} customers) ---`);
        segments[tier].forEach(name => console.log(`- ${name}`));
    });

    rl.close();
}

getFilePath();