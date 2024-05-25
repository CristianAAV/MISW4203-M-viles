
import { join } from 'path';
const androidAppPath = join(process.cwd(), "app/app-debug.apk");
export const config = {

    port: 4723,

    specs: [

       './test_e2e/specs/**/*.js'
    ],
    exclude: [
    ],
    maxInstances: 1,
    capabilities: [{


        platformName: "Android",
        "appium:deviceName": "Pixel 8",
        "appium:platformVersion": "11",
        "appium:automationName": "UIAutomator2",
        "appium:app":androidAppPath,


    }],

    logLevel: 'info',
    bail: 0,
    waitforTimeout: 20000,
    connectionRetryTimeout: 120000,
    connectionRetryCount: 3,
    services: ['appium'],
    framework: 'mocha',
    reporters: ['spec'],
    mochaOpts: {
        ui: 'bdd',
        timeout: 60000
    },
}
