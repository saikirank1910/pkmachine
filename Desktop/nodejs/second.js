var sum = 0;
for (i = 2; i < process.argv.length; i++) {
    sum += Number(process.argv[i]);
}

console.log(sum);
var fs = require('fs');
var content = fs.readFileSync(process.argv[2], "utf8");
var lines=content.split("\n");

console.log(lines.length-1); 