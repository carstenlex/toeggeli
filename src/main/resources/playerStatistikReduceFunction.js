function(key, values) {
    reduced = {win: 0, loss:0};
    for (var idx = 0; idx < values.length; idx++){
        reduced.win += values[idx].win;
        reduced.loss += values[idx].loss;
    }
    return reduced;
}