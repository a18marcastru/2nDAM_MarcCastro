const {spawn} = require('child_process')

// const ls spawn('ls', ['-l', '-a']) Comanda de linux
// const ls = spawn('cmd', ['/c', 'dir', '/a', '/q'])
const ls = spawn('dir', {shell: true})

ls.stdout.on('data', (data) => {
    console.log(`stdout: ${data}`)
});

ls.on('close', (code) => {
    console.log(`child process exited with code ${code}`)
})