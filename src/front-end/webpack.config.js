const path = require('path');

const config = {
    mode: 'development',
    entry: { validation: './src/registerForm.js', search: './src/search.js' },

    module: {
        rules: [
            {
                test: /\.m?js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        "env": {
                            "test": {
                                "plugins": [["istanbul", {
                                    exclude: ['**/*-spec.js']
                                }]]
                            }
                        },
                        presets: [
                            [
                                "@babel/preset-env",
                                {
                                    "targets": "last 5 versions"
                                }
                            ]
                        ]
                    }
                }
            }
        ]
    },
    output: {
        path: path.resolve("../main/resources/static/js", 'dist'),
        filename: '[name].js'
    }
};
const esConfig = {
    devtool: 'source-map',
    mode: 'development',
    entry: { validation: './src/main.js', search: './src/search.js' },
    module: {
        rules: [
            {
                test: /\.m?js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        "env": {
                            "test": {
                                "plugins": [["istanbul", {
                                    exclude: ['**/*-spec.js']
                                }]]
                            }
                        },
                        presets: [
                            [
                                "@babel/preset-env",
                                {
                                    useBuiltIns: "usage",
                                    "debug": true,
                                    targets: {
                                        esmodules: true
                                    }
                                }
                            ]
                        ]
                    }
                }
            }
        ]
    },
    plugins: [],
    output: {
        path: path.resolve("../main/resources/static/js", 'dist'),
        filename: '[name].m.js'
    }
};

module.exports = [
    config,
    esConfig
];