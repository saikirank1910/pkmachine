var fs = require('fs');
var content = fs.readFileSync(process.argv[2], "utf8");
var lines=content.split("\n");

console.log(lines.length-1); 