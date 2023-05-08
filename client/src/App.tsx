import { css } from '@emotion/react';
import { useState } from 'react';
// eslint-disable-next-line import/no-unresolved -- this does actually seem to bundle okay, but eslint isn't happy...
import viteLogo from '/vite.svg';
import reactLogo from './assets/react.svg';

export function App() {
	const [count, setCount] = useState(1);

	return (
		<div
			css={css`
				display: flex;
				flex-direction: column;
				align-items: center;
				margin-top: 5rem;
			`}
		>
			<header>
				<a href="https://vitejs.dev" target="_blank" rel="noreferrer">
					<img src={viteLogo} className="logo" alt="Vite logo" />
				</a>
				<a href="https://reactjs.org" target="_blank" rel="noreferrer">
					<img src={reactLogo} className="logo react" alt="React logo" />
				</a>
				<h1>Play, Vite & React Starter Project</h1>
			</header>
			<main className="card">
				<button onClick={() => setCount((count) => count + 1)}>
					count is {count}
				</button>
				<p>
					Edit <code>src/App.tsx</code> and save to test HMR
				</p>
				<p className="read-the-docs">
					Click on the Vite and React logos to learn more
				</p>
			</main>
		</div>
	);
}
