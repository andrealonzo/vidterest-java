module.exports = {
    entry: {
        index:"./src/site/components/Index.jsx"
    },
    output: {
        path: __dirname,
        filename: "./src/main/webapp/js/[name]Bundle.js"
    },
     module: {
        loaders: [
            {
                //tell webpack to use jsx-loader for all *.jsx files
                test: /\.jsx$/,
                loader: 'jsx-loader?insertPragma=React.DOM&harmony'
            },
            // SASS
            {
              test: /\.scss$/,
              loader: 'style!css!sass'
            }
        ]
    },
    externals: {
        //don't bundle the 'react' npm package with our bundle.js
        //but get it from a global 'React' variable
        'react': 'React',
        'react-dom': 'ReactDOM'
    },
    resolve: {
        extensions: ['', '.js', '.jsx']
    }
};