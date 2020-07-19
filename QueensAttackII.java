static int queensAttack(int n, int k, int queenRow, int queenCol, int[][] obstacles) {
        if(n <= 1) return 0;

        //obstacle coordinates. we will store closest obstacle only
        //initially all values are initialized to max i.e. edge of board + 1
        int topRow = n + 1, topCol = queenCol;
        int leftRow = queenRow, leftCol = 0;
        int rightRow = queenRow, rightCol = n + 1;
        int bottomRow = 0, bottomCol = queenCol;

        int leftDistance = queenCol - 1,
                rightDistance = n - queenCol,
                topDistance = n - queenRow,
                bottomDistance = queenRow - 1;

        int topLeftRow, topLeftCol;
        if(leftDistance <= topDistance) {
            topLeftRow = queenRow + leftDistance + 1;
            topLeftCol = (queenCol - leftDistance) - 1;
        }
        else {
            topLeftRow = queenRow + topDistance + 1;
            topLeftCol = (queenCol - topDistance) - 1;
        }

        int topRightRow, topRightCol;
        if(rightDistance <= topDistance) {
            topRightRow = queenRow + rightDistance + 1;
            topRightCol = queenCol + rightDistance + 1;
        }
        else {
            topRightRow = queenRow + topDistance + 1;
            topRightCol = queenCol + topDistance + 1;
        }

        int bottomLeftRow, bottomLeftCol;
        if(bottomDistance <= leftDistance) {
            bottomLeftRow = (queenRow - bottomDistance) - 1;
            bottomLeftCol = (queenCol - bottomDistance) - 1;
        }
        else {
            bottomLeftRow = (queenRow - leftDistance) - 1;
            bottomLeftCol = (queenCol - leftDistance) - 1;
        }

        int bottomRightRow, bottomRightCol;
        if(bottomDistance <= rightDistance) {
            bottomRightRow = (queenRow - bottomDistance) - 1;
            bottomRightCol = queenCol + bottomDistance + 1;
        }
        else {
            bottomRightRow = (queenRow - rightDistance) - 1;
            bottomRightCol = queenCol + rightDistance + 1;
        }

        //now we have our limits set
        //next we need to loop through all obstacles
        //check if obstacle is lined up with queen
        //if it is then compare with saved coords
        //if its distance is less than saved, update saved
        //at the end we will have closest obstacles coordinates
        //and if no obstacle then maximum values (edge of board)

        for (int i = 0; i < k; i++) {
            int obstacleRow = obstacles[i][0];
            int obstacleCol = obstacles[i][1];

            //top left
            if(obstacleRow > queenRow && (queenRow + queenCol) == (obstacleRow + obstacleCol)) {
                int obstacleDistance = obstacleRow - queenRow;
                int savedDistance = topLeftRow - queenRow;
                if(obstacleDistance < savedDistance) {
                    topLeftRow = obstacleRow;
                    topLeftCol = obstacleCol;
                }
            }

            //top right
            else if(obstacleRow > queenRow && (queenRow - queenCol) == (obstacleRow - obstacleCol)) {
                int obstacleDistance = obstacleCol - queenCol;
                int savedDistance = topRightCol - queenCol;
                if(obstacleDistance < savedDistance) {
                    topRightRow = obstacleRow;
                    topRightCol = obstacleCol;
                }
            }

            //bottom left
            else if(obstacleRow < queenRow && (queenRow - queenCol) == (obstacleRow - obstacleCol)) {
                int obstacleDistance = queenCol - obstacleCol;
                int savedDistance = queenCol - bottomLeftCol;
                if(obstacleDistance < savedDistance) {
                    bottomLeftRow = obstacleRow;
                    bottomLeftCol = obstacleCol;
                }
            }

            //bottom right
            else if(obstacleRow < queenRow && (queenRow + queenCol) == (obstacleRow + obstacleCol)) {
                int obstacleDistance = obstacleCol - queenCol;
                int savedDistance = bottomRightCol - queenCol;
                if(obstacleDistance < savedDistance) {
                    bottomRightRow = obstacleRow;
                    bottomRightCol = obstacleCol;
                }
            }

            //top
            else if(obstacleRow > queenRow && obstacleCol == queenCol) {
                int obstacleDistance = obstacleRow - queenRow;
                int savedDistance = topRow - queenRow;
                if(obstacleDistance < savedDistance) {
                    topRow = obstacleRow;
                    topCol = obstacleCol;
                }
            }

            //bottom
            else if(obstacleRow < queenRow && obstacleCol == queenCol) {
                int obstacleDistance = queenRow - obstacleRow;
                int savedDistance = queenRow - bottomRow;
                if(obstacleDistance < savedDistance) {
                    bottomRow = obstacleRow;
                    bottomCol = obstacleCol;
                }
            }

            //left
            else if(obstacleRow == queenRow && obstacleCol < queenCol) {
                int obstacleDistance = queenCol - obstacleCol;
                int savedDistance = queenCol - leftCol;
                if(obstacleDistance < savedDistance) {
                    leftRow = obstacleRow;
                    leftCol = obstacleCol;
                }
            }

            //right
            else if(obstacleRow == queenRow && obstacleCol > queenCol) {
                int obstacleDistance = obstacleCol - queenCol;
                int savedDistance = rightCol - queenCol;
                if(obstacleDistance < savedDistance) {
                    rightRow = obstacleRow;
                    rightCol = obstacleCol;
                }
            }
        }

        int count = 0;

        count += topLeftRow - queenRow; //top left
        count += topRightRow - queenRow; //top right
        count += topRow - queenRow; //top
        count += queenCol - leftCol; //left
        count += rightCol - queenCol; //right
        count += bottomRightCol - queenCol; //bottom right
        count += queenCol - bottomLeftCol; //bottomLeft
        count += queenRow - bottomRow; //bottom

        count -= 8;

        return count;
    }
