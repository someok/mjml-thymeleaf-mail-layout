module.exports = {
    root: true,
    extends: [
        // '@someok/eslint-config-react',
        'plugin:prettier/recommended',
    ],
    env: {
        es6: true,
    },
    parserOptions: {
        ecmaVersion: 10,
        sourceType: 'module',
        ecmaFeatures: {
            jsx: true,
        },
    },
    rules: {},
    globals: {
        __DEV__: false,
        __REDUX_LOGGER__: false,
        __CTX__: false,
    },
};
