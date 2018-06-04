var fs = require('fs');
var path = require('path');
fs.readdir(process.argv[2], function(err, list) {
if (err) {
      return console.log(err);
         }
 for(i=0;i<list.length;i++){
if(path.extname(list[i]).match(process.argv[3])){
    console.log(list[i]);
}
 }
      });