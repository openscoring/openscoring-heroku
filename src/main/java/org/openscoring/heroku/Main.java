/*
 * Copyright (c) 2015 Villu Ruusmann
 *
 * This file is part of Openscoring
 *
 * Openscoring is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Openscoring is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Openscoring.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openscoring.heroku;

import java.io.File;

import com.beust.jcommander.Parameter;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.openscoring.client.DirectoryDeployer;

public class Main extends org.openscoring.server.Main {

	@Parameter (
		names = {"--model-dir"},
		description = "PMML model auto-deployment directory"
	)
	private File modelDir = null;


	static
	public void main(String... args) throws Exception {
		execute(Main.class, args);
	}

	@Override
	public void run() throws Exception {
		Server server = createServer();

		server.start();

		if(this.modelDir != null){
			Connector[] connectors = server.getConnectors();
			if(connectors.length != 1){
				throw new IllegalStateException();
			}

			@SuppressWarnings (
				value = {"resource"}
			)
			ServerConnector connector = (ServerConnector)connectors[0];

			final
			DirectoryDeployer deployer = new DirectoryDeployer();
			deployer.setDir(this.modelDir);
			deployer.setModelCollection("http://" + connector.getHost() + ":" + String.valueOf(connector.getPort()) + "/openscoring/model");

			Thread deployerThread = new Thread(){

				@Override
				public void run(){

					try {
						deployer.run();
					} catch(Exception e){
						e.printStackTrace(System.err);
					}
				}
			};

			deployerThread.start();
		}

		server.join();
	}
}