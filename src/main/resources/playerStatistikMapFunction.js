function() {
    if (this.score1==10){
        emit (this.team1.player1, {win: 1, loss:0});
        emit (this.team1.player2, {win: 1, loss:0});
        emit (this.team2.player1, {win: 0, loss:1});
        emit (this.team2.player2, {win: 0, loss:1});
    }else if (this.score2==10) {
        emit (this.team2.player1, {win:1,loss:0});
        emit (this.team2.player2, {win:1,loss:0});
        emit (this.team1.player1, {win:0,loss:1});
        emit (this.team1.player2, {win:0,loss:1});
    }
}