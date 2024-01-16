# RentX - UI

## Development

### Prerequisites

- [NodeJS](https://nodejs.org/en) `v20.x`
> you can use [nvm](https://github.com/nvm-sh/nvm) to manage node versions
- [pnpm](https://pnpm.io/installation#using-npm) `v8.x`

check if exists

```sh
node --version
pnpm --version
```

### Commands

<table>
<tbody>
<tr>
<th>&nbsp;title</th>
<th>command</th>
<th>&nbsp;description</th>
</tr>
<tr>
<td>development</td>
<td>

```
pnpm dev
```

</td>
<td>starts development server</td>
</tr>
<tr>
<td>build</td>
<td>

```
pnpm build
```

</td>
<td>generates bundle files in <u>build</u> directory</td>
</tr>
<tr>
<td>preview build</td>
<td>

```
pnpm preview
```

</td>
<td>preview of generated bundle by <i>build</i> command</td>
</tr>
<tr>
<td>lint</td>
<td>

```
pnpm lint
```

</td>
<td>find the code formatting errors and enforce consistent code style</td>
</tr>
<tr>
<td>fix lint</td>
<td>

```
pnpm fix:lint
```

</td>
<td>automatically fix most lint errors</td>
</tr>
<tr>
<td>test</td>
<td>

```
pnpm test
```

</td>
<td>run unit tests</td>
</tr>
<tr>
<td>watch test</td>
<td>

```
pnpm test:watch
```

</td>
<td>run unit tests in watch mode</td>
</tr>
</tbody>
</table>

### Reference of technologies used

- [React](https://react.dev/)
- [React Router](https://reactrouter.com/en/main)
- [Typescript](https://www.typescriptlang.org/)
- [Vite](https://vitejs.dev/)
- [Tailwind](https://tailwindcss.com/)
- [LessCSS](https://lesscss.org/)
- [Antd](https://ant.design/)

### Opinionated Dev Environment

- Use `Visual Studio Code - v1.82.2`.
- Install extensions
    - [ESLint](https://marketplace.visualstudio.com/items?itemName=dbaeumer.vscode-eslint)
    - [Prettier - Code formatter](https://marketplace.visualstudio.com/items?itemName=esbenp.prettier-vscode)
    - [Tailwind CSS IntelliSense](https://marketplace.visualstudio.com/items?itemName=bradlc.vscode-tailwindcss)
- create file at `.vscode/settings.json` from root of this repository and add the below snippet

```json
{
    "eslint.format.enable": true,
    "editor.codeActionsOnSave": {
        "source.fixAll.eslint": true 
    },
    "eslint.workingDirectories": [
        {
            "directory": "./ui",
            "changeProcessCWd": true
        }
    ]
}
```

This will automatically fix lint errors and apply code format defined in eslint and prettier on save file event.

### Credits

- Illustrations - https://undraw.co/illustrations
