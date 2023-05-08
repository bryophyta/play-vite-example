// eslint-disable-next-line import/no-unresolved -- todo -- fix this issue (this is needed for dev server)
import 'vite/modulepreload-polyfill';
import * as React from 'react';
import * as ReactDOM from 'react-dom/client';
import { App } from './App';

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
	<React.StrictMode>
		<App />
	</React.StrictMode>,
);
