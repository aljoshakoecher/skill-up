package method2;

import org.eclipse.milo.opcua.sdk.core.ValueRank;
import org.eclipse.milo.opcua.sdk.server.api.methods.AbstractMethodInvocationHandler.InvocationContext;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import annotations.InputArgument;
import annotations.InputArguments;
import annotations.OpcUaMethod;
import annotations.OutputArgument;
import annotations.OutputArguments;
import annotations.UaTypes;
import methodRegistration.MethodRegistration;

/**
 * @Component Indicates that annotated class is intended to be an OSGi
 *            component. <br>
 *            After becoming satisfied component is registered as a service
 *            under MethodRegistration
 */
@OpcUaMethod
@Component
public class SimpleMethod2 implements MethodRegistration {

	private final Logger logger = LoggerFactory.getLogger(SimpleMethod2.class);

	/**
	 * Method that turns input arguments into output argument
	 */
	@Override
	@InputArguments({
			@InputArgument(name = "1. Zahl", dataType = UaTypes.Double, valueRank = ValueRank.Scalar, arrayDimensions = "", description = "Minuend"),
			@InputArgument(name = "2. Zahl", dataType = UaTypes.Double, valueRank = ValueRank.Scalar, arrayDimensions = "", description = "Substrahend") })
	@OutputArguments({
			@OutputArgument(name = "Ergebnis", dataType = UaTypes.Double, valueRank = ValueRank.Scalar, arrayDimensions = "", description = "Differenz") })
	public Variant[] invoke(InvocationContext context, Variant[] inputValues) throws UaException {
		logger.debug("Invoking simpleMethod of objectId={}", context.getObjectId());

		double firstNumber = (double) inputValues[0].getValue();
		double secondNumber = (double) inputValues[1].getValue();
		double sum = firstNumber - secondNumber;

		return new Variant[] { new Variant(sum) };
	}

	@Activate
	public void activate() {
		logger.info("Methode wird aktiviert.");
	}

	@Deactivate
	public void deactivate() {
		logger.info("Methode wird deaktiviert.");
	}
}