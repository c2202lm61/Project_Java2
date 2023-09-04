#!/bin/bash

#!/bin/bash

# Lấy vị trí hiện tại của file .sh
current_directory="$(cd "$(dirname "$0")" && pwd)"
cd $current_directory
java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -jar out/artifacts/Project_Java_2_jar/Project_Java_2.jar