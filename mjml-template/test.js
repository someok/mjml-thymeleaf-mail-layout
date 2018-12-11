const a = {
    a1: 'aa',
    a2: 'bb',
};

if (a) {
    console.log('aaa');

    [(-1, 1)].forEach(delta => console.log(delta * 20));
}
